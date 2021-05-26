package com.mcal.apkprotector.async.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.mcal.apkprotector.App
import com.mcal.apkprotector.App.Companion.getContext
import com.mcal.apkprotector.async.ProtectAsyncListener
import com.mcal.apkprotector.data.Constants
import com.mcal.apkprotector.data.Preferences
import com.mcal.apkprotector.fastzip.FastZip
import com.mcal.apkprotector.patchers.ManifestPatcher
import com.mcal.apkprotector.signer.SignatureTool
import com.mcal.apkprotector.task.AndResGuard
import com.mcal.apkprotector.task.DexCrypto
import com.mcal.apkprotector.utils.*
import com.mcal.apkprotector.zipalign.ZipAlign
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.svolf.melissa.sheet.SweetViewDialog
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.coroutines.CoroutineContext


class ProtectAsync(
    @field:SuppressLint("StaticFieldLeak") private val listener: ProtectAsyncListener,
    @field:SuppressLint("StaticFieldLeak") private val context: Context
) : CoroutineScope {
    private val path: String = ScopedStorage.getStorageDirectory().toString() + "/ApkProtect"
    private val xpath: String = context.filesDir.absolutePath
    private var mi: MyAppInfo? = null
    private var protectLoadDialog: SweetViewDialog? = null
    private val dp16 = App.dpToPx(16)
    private val dp96 = App.dpToPx(96)

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    fun execute(vararg array: String?) = launch {
        onPreExecute()
        val result = doInBackground(*array)
        onPostExecute(result)
    }

    private fun onPreExecute() {
        showProgressDialog()
        listener.onProcess()
        FileUtils.deleteDir(File(Constants.OUTPUT_PATH))
        FileUtils.deleteDir(File(Constants.RELEASE_PATH))
        FileUtils.delete(File(Constants.LOG_PATH))
        FileUtils.delete(File(Constants.CACHE_PATH))
    }

    private fun onProgressUpdate(vararg values: String?) {
        protectLoadDialog!!.setTitle(values[0])
    }

    private fun onPostExecute(result: Boolean) {
        dismissProgressDialog()
        System.gc()
        FileUtils.deleteDir(File(Constants.OUTPUT_PATH))
        FileUtils.deleteDir(File(Constants.RELEASE_PATH))
        FileUtils.deleteDir(File(Constants.CACHE_PATH))
        FileUtils.deleteDir(File(Constants.SMALI_PATH))
        if (result) {
            listener.onCompleted()
        } else {
            val sourceDir =
                File(ScopedStorage.getStorageDirectory().absolutePath + "/ApkProtect/output/" + MyAppInfo.getPackage() + "")
            if (sourceDir.exists()) {
                FileUtils.deleteDir(sourceDir)
            }
            listener.onFailed()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun doInBackground(vararg p1: String?): Boolean = withContext(Dispatchers.IO) {
        var t = false
        mi = MyAppInfo(context, p1[0])
        doProgress("Compiling…")
        if (Preferences.getDexProtectBoolean()) {

            LoggerUtils.writeLog("----------ApkProtector running----------------")

            LoggerUtils.writeLog("Default dir of ApkProtector" + System.getProperty("user.dir"))

            val outputFolder = File(Constants.OUTPUT_PATH)
            if (!outputFolder.exists()) {
                outputFolder.mkdir()
                LoggerUtils.writeLog("Dir created: " + outputFolder.absolutePath)
            }

            val dexesFolder = File(Constants.ASSETS_PATH + File.separator + "apkprotector_dex")
            if (!dexesFolder.exists()) {
                dexesFolder.mkdir()
                LoggerUtils.writeLog("Dir created: " + dexesFolder.absolutePath)
            }

            val releaseFolder = File(Constants.RELEASE_PATH)
            if (!releaseFolder.exists()) {
                releaseFolder.mkdir()
                LoggerUtils.writeLog("Dir created: " + releaseFolder.absolutePath)
            }

            val smaliFolder = File(Constants.SMALI_PATH)
            if (!smaliFolder.exists()) {
                smaliFolder.mkdir()
                LoggerUtils.writeLog("Dir created: " + smaliFolder.absolutePath)
            }

            val time = System.currentTimeMillis()

            LoggerUtils.writeLog("Work time: " + (System.currentTimeMillis() - time))

            try {
                val smaliPath = Constants.SMALI_PATH + File.separator + "ProtectApplication.smali"
                FileCustomUtils.inputStreamAssets(getContext(), "application.smali", smaliPath)

                doProgress("Decompiling…")
                FastZip.extract(p1[0], Constants.OUTPUT_PATH)
                LoggerUtils.writeLog("Success unpack: " + p1[0])
                doProgress("Patching manifest…")
                val bis = ByteArrayInputStream(ManifestPatcher.parseManifest())
                val fos = FileOutputStream(Constants.MANIFEST_PATH)
                val buffer = ByteArray(2048)
                var len = 0
                while (bis.read(buffer).also { len = it } > 0) {
                    fos.write(buffer, 0, len)
                }
                fos.close()
                LoggerUtils.writeLog("Success patch: " + Constants.MANIFEST_PATH)
                doProgress("Encrypting dexes…")
                DexCrypto.encodeDexes()
                LoggerUtils.writeLog("Dex files successful encrypted")
                doProgress("Compiling…")
                FastZip.repack(p1[0], Constants.UNSIGNED_PATH)
                LoggerUtils.writeLog("Success compiled: " + Constants.UNSIGNED_PATH)
                SourceInfo.initialise("$path/output", mi!!)
                doProgress("Signing Apk…")
                if (Preferences.getZipAlignerBoolean()) {
                    doProgress("Aligning Apk…")
                    if (ZipAlign.runProcess(Constants.UNSIGNED_PATH, "$xpath/output/aligned.apk")) {
                        LoggerUtils.writeLog("APK aligned")
                        doProgress("Signing Apk…")
                        if (SignatureTool.sign(
                                context,
                                File("$xpath/output/aligned.apk"),
                                File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                            )
                        ) {
                            LoggerUtils.writeLog("APK signed")
                            doProgress("Done")
                            t = true
                        }
                    }
                } else {
                    doProgress("Signing Apk…")
                    if (SignatureTool.sign(
                            context,
                            File(Constants.UNSIGNED_PATH),
                            File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                        )
                    ) {
                        LoggerUtils.writeLog("APK signed")
                        doProgress("Done")
                        t = true
                    }
                }
            } catch (e: Exception) {
                LoggerUtils.writeLog("$e")
            }

            //FileUtils.delete(File(Constants.OUTPUT_PATH))
            //FileUtils.delete(File(Constants.UNSIGNED_PATH))
            //FileUtils.delete(File(Constants.CACHE_PATH))

            /*if (FileUtils.copyFileStream(File(p1[0]), File("$xpath/output/app.apk"))) {
                if (ZipUtils.unpack("$xpath/output/app.apk", "$xpath/gen/")) {
                    if (ManifestPatcher.manifestPatch(xpath + "/gen/AndroidManifest.xml")) {
                        doProgress("DEX - Optimising…")
                        if (DexEncrypt.enDex(context)) {
                            //if (DexPatching.renameAppClass(context)) {
                            doProgress("Dex - Merging…")

                            if (renameAppClass()) {
                                //DexEncrypt.addMyDex(context)
                                doProgress("Building APK…")
                                if (ZipUtils.pack("$xpath/gen", "$xpath/output/unsigned.apk")) {
                                    SourceInfo.initialise("$path/output", mi!!)
                                    if (Preferences.getZipAlignerBoolean()) {
                                        doProgress("Aligning Apk…")
                                        if (ZipAlign.runProcess(
                                                "$xpath/output/unsigned.apk",
                                                "$xpath/output/aligned.apk"
                                            )
                                        ) {
                                            doProgress("Signing Apk…")
                                            if (SignatureTool.sign(
                                                    context,
                                                    File("$xpath/output/aligned.apk"),
                                                    File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                                                )
                                            ) {
                                                doProgress("Done")
                                                t = true
                                            }
                                        }
                                    } else {
                                        doProgress("Signing Apk…")
                                        if (SignatureTool.sign(
                                                context,
                                                File("$xpath/output/unsigned.apk"),
                                                File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                                            )
                                        ) {
                                            doProgress("Done")
                                            t = true
                                        }
                                    }
                                }
                            }

                            //}
                        }
                    }
                }
            }*/
        }
        if (Preferences.getEncryptResourcesBoolean()) {
            doProgress("Copying apk…")
            if (FileUtils.copyFileStream(File(p1[0]), File("$xpath/output/app.apk"))) {
                doProgress("Encrypting Resources…")
                AndResGuard.proguard2(
                    File("$xpath/output/app.apk"),
                    File(path + "/output/" + MyAppInfo.getPackage() + "/"),
                    File(path),
                    MyAppInfo.getPackage()
                )
                SourceInfo.initialise("$path/output", mi!!)
                if (Preferences.getZipAlignerBoolean()) {
                    //if (ZipAlign.fnAapt(context, "$xpath/output/app.apk", "$xpath/output/aligned.apk")) {
                    doProgress("Aligning Apk…")
                    if (ZipAlign.runProcess("$xpath/output/app.apk", "$xpath/output/aligned.apk")) {
                        doProgress("Signing Apk…")
                        if (SignatureTool.sign(
                                context,
                                File("$xpath/output/aligned.apk"),
                                File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                            )
                        ) {
                            doProgress("Done")
                            t = true
                        }
                    }
                } else {
                    doProgress("Signing Apk…")
                    if (SignatureTool.sign(
                            context,
                            File("$xpath/output/app.apk"),
                            File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                        )
                    ) {
                        doProgress("Done")
                        t = true
                    }
                }
            }
        }
        if (Preferences.getSignApkBoolean()) {
            doProgress("Copying apk…")
            if (FileUtils.copyFileStream(File(p1[0]), File("$xpath/output/app.apk"))) {
                SourceInfo.initialise("$path/output", mi!!)
                if (Preferences.getZipAlignerBoolean()) {
                    doProgress("Aligning Apk…")
                    if (ZipAlign.runProcess("$xpath/output/app.apk", "$xpath/output/aligned.apk")) {
                        doProgress("Signing Apk…")
                        if (SignatureTool.sign(
                                context,
                                File("$xpath/output/aligned.apk"),
                                File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                            )
                        ) {
                            doProgress("Done")
                            t = true
                        }
                    }
                } else {
                    doProgress("Signing Apk…")
                    if (SignatureTool.sign(
                            context,
                            File("$xpath/output/app.apk"),
                            File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                        )
                    ) {
                        doProgress("Done")
                        t = true
                    }
                }
            }
        }
        return@withContext t
    }

    private suspend fun doProgress(value: String?) = withContext(coroutineContext) {
        onProgressUpdate(value)
    }

    private fun showProgressDialog() {
        if (protectLoadDialog == null) {
            protectLoadDialog = SweetViewDialog(context).apply {
                setTitleTextSize(14f)
                setView(makeView(context))
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                setTitle("Loading installed applications...")
            }
        }
        protectLoadDialog!!.show()
    }

    private fun dismissProgressDialog() {
        if (protectLoadDialog != null && protectLoadDialog!!.isShowing) {
            protectLoadDialog!!.dismiss()
        }
    }

    private fun makeView(ctx: Context): View {
        return RelativeLayout(ctx).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            addView(ProgressBar(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(dp96, dp96)
                isIndeterminate = false
                setPadding(dp16, dp16, dp16, dp16)
            })
        }
    }
}