package com.mcal.dexprotect.async.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ThumbnailUtils
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.ImageView
import com.mcal.dexprotect.App.Companion.dpToPx
import com.mcal.dexprotect.App.Companion.getContext
import com.mcal.dexprotect.R
import com.mcal.dexprotect.utils.file.PathF
import kotlinx.coroutines.*
import org.jetbrains.annotations.Contract
import java.io.File
import java.util.*
import kotlin.coroutines.CoroutineContext

enum class GetIcon {
    INSTANCE;

    private var allowThumbnails = true
    private val assocAll: HashSet<*> = HashSet<Any?>()
    private val cache = HashMap<String, Drawable?>()
    private var disableBackground = false
    var imageExt: List<*> = Arrays.asList("jpg", "jpeg", "png", "bmp", "gif")
    private val mime = MimeTypeMap.getSingleton()
    private val packMan = getContext()!!.packageManager
    private var recreateMainActivityTime: Long = 0

    enum class FileType {
        NORMAL, APK, IMAGE
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class RetrieveFileIcon(
        var origName: String,
        var imageView: ImageView,
        var type: FileType
    ) : CoroutineScope {
        private var iconLoader = Job();
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main + iconLoader

        fun execute() = launch {
            val result = doInBackground()
            onPostExecute(result)
        }

        private suspend fun doInBackground(): Drawable? = withContext(Dispatchers.IO) {
            if (disableBackground) {
                return@withContext null
            }
            val tag = imageView.tag as String
            if (tag != origName) {
                return@withContext null
            }
            if (type == FileType.APK) {
                return@withContext getApkIcon(origName)
            }
            return@withContext if (type == FileType.IMAGE) getImageIcon(origName) else null
        }

        private suspend fun onPostExecute(drawable: Drawable?) = withContext(coroutineContext) {
            if (drawable != null) {
                if (cache.size > MAX_CACHE_SIZE) {
                    cache.clear()
                }
                if (!disableBackground) {
                    cache[origName] = drawable
                }
                if (imageView.tag == origName) {
                    imageView.setImageDrawable(drawable)
                }
            }
        }

    }

    private fun getApkIcon(str: String): Drawable? {
        var drawable: Drawable? = null
        val packageArchiveInfo = packMan.getPackageArchiveInfo(str, PackageManager.GET_META_DATA)
        if (packageArchiveInfo != null) {
            packageArchiveInfo.applicationInfo.apply {
                sourceDir = str
                publicSourceDir = str
            }
            try {
                drawable = packageArchiveInfo.applicationInfo.loadIcon(packMan)
            } catch (ignored: Exception) {
            }
        }
        return drawable
    }

    private fun getImageIcon(origName: String): Drawable? {
        val ratio = dpToPx(48)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(origName, options)
        options.inSampleSize = 1
        while (options.outHeight >= 192 && options.outWidth >= 192) {
            options.apply {
                outHeight /= 2
                outWidth /= 2
                inSampleSize *= 2
            }
        }
        options.inJustDecodeBounds = false
        val decodeFile = BitmapFactory.decodeFile(origName, options) ?: return null
        return try {
            BitmapDrawable(
                getContext()!!.resources,
                ThumbnailUtils.extractThumbnail(decodeFile, ratio, ratio)
            )
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "getImageIcon: ", e)
            null
        }
    }

    fun backgroundLoadIcon(filePath: String, imageView: ImageView, fileType: FileType) {
        getIcon(true, filePath, filePath, imageView)
        RetrieveFileIcon(filePath, imageView, fileType).execute()
    }

    fun setDisableBackground(disableBackground: Boolean) {
        this.disableBackground = disableBackground
        if (System.currentTimeMillis() - recreateMainActivityTime < 3000) {
            this.disableBackground = false
        }
    }

    fun onActivityRecreate() {
        recreateMainActivityTime = System.currentTimeMillis()
    }

    fun setConfig() {
        if (!allowThumbnails) {
            cache.clear()
        }
        allowThumbnails = true
    }

    fun resolve(filePath: String, holderView: ImageView) {
        holderView.tag = filePath
        val fileExt = PathF.getExt(filePath)
        var fileType = FileType.NORMAL
        if (fileExt == "apk") {
            fileType = FileType.APK
        } else if (imageExt.contains(fileExt)) {
            fileType = FileType.IMAGE
        }
        backgroundLoadIcon(filePath, holderView, fileType)
    }

    private fun getIcon(
        curDirNull: Boolean,
        fileName: String?,
        filePath: String,
        imageView: ImageView
    ) {
        val fileExt = PathF.getExt(fileName).toLowerCase(Locale.ROOT)
        if (fileExt.isEmpty()) {
            imageView.setImageResource(R.drawable.ic_file)
            return
        }
        var fileType = FileType.NORMAL
        if (fileExt == "apk") {
            fileType = FileType.APK
        } else if (imageExt.contains(fileExt)) {
            fileType = FileType.IMAGE
        }
        var drawable: Drawable?
        if (curDirNull || !allowThumbnails || fileType == FileType.NORMAL) {
            drawable = cache[fileExt]
            if (drawable != null) {
                imageView.setImageDrawable(drawable)
                return
            } else if (cache.containsKey(fileExt)) {
                imageView.setImageResource(R.drawable.ic_file)
                return
            } else {
                val mimeTypeFromExtension =
                    mime.getMimeTypeFromExtension(PathF.getExt(fileName).toLowerCase())
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(Uri.fromFile(File(fileName)), mimeTypeFromExtension)
                val queryIntentActivities: List<*> =
                    packMan.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
                var iconId = -1
                for (i in queryIntentActivities.indices) {
                    val resolveInfo = queryIntentActivities[i] as ResolveInfo
                    if ((iconId == -1 || resolveInfo.activityInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0) && !assocAll.contains(
                            resolveInfo.activityInfo.applicationInfo.packageName
                        )
                    ) {
                        iconId = i
                    }
                }
                drawable = if (iconId != -1) {
                    (queryIntentActivities[iconId] as ResolveInfo).loadIcon(packMan)
                } else {
                    null
                }
                if (cache.size > MAX_CACHE_SIZE) {
                    cache.clear()
                }
                if (!disableBackground) {
                    cache[fileExt] = drawable
                }
                if (drawable == null) {
                    imageView.setImageResource(R.drawable.ic_file)
                    return
                } else {
                    imageView.setImageDrawable(drawable)
                    return
                }
            }
        }
        drawable = cache[filePath]
        if (drawable != null) {
            imageView.setImageDrawable(drawable)
        } else {
            GlobalScope.launch {
                backgroundLoadIcon(filePath, imageView, fileType)
            }
        }
    }

    companion object {
        private const val MAX_CACHE_SIZE = 512
        private const val TAG = "GetIcon"

        @JvmStatic
        @get:Contract(pure = true)
        val instance: GetIcon
            get() = INSTANCE
    }
}