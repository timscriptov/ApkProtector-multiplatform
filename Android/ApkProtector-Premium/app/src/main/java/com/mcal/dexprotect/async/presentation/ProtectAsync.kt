package com.mcal.dexprotect.async.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Build.VERSION
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.mcal.dexprotect.App
import com.mcal.dexprotect.App.Companion.getContext
import com.mcal.dexprotect.BuildConfig
import com.mcal.dexprotect.async.ProtectAsyncListener
import com.mcal.dexprotect.data.Constants
import com.mcal.dexprotect.data.Preferences
import com.mcal.dexprotect.fastzip.FastZip
import com.mcal.dexprotect.patchers.ManifestPatcher
import com.mcal.dexprotect.signer.SignatureTool
import com.mcal.dexprotect.task.AndResGuard
import com.mcal.dexprotect.task.DexCrypto
import com.mcal.dexprotect.utils.*
import com.mcal.dexprotect.utils.file.FileUtils
import com.mcal.dexprotect.utils.file.ScopedStorage
import com.mcal.dexprotect.zipalign.ZipAlign
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

    private fun onPostExecute(result: Boolean) {
        dismissProgressDialog()
        System.gc()
        if (!BuildConfig.DEBUG) {
            FileUtils.deleteDir(File(Constants.OUTPUT_PATH))
            FileUtils.deleteDir(File(Constants.RELEASE_PATH))
            FileUtils.deleteDir(File(Constants.CACHE_PATH))
            FileUtils.deleteDir(File(Constants.SMALI_PATH))
        }

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

    private suspend fun doInBackground(vararg p1: String?): Boolean = withContext(Dispatchers.IO) {
        var t = false
        mi = MyAppInfo(context, p1[0])
        doProgress("Compiling…")

        deviceInformation()
        LoggerUtils.writeLog("\n************ APKPROTECTOR RUNNING ***********")
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

        if (Preferences.getDexProtectBoolean()) {
            try {
                val smaliPath = Constants.SMALI_PATH + File.separator + "ProtectApplication.smali"
                FileUtils.inputStreamAssets(getContext(), "application.smali", smaliPath)

                if(Preferences.getTypeHideApkProtector().equals("2")) {
                    generateRandom()
                }
                doProgress("Decompiling…")
                FileUtils.copyFileStream(
                    File(p1[0]),
                    File(Constants.RELEASE_PATH + File.separator + "app-temp.apk")
                )
                FastZip.extract(p1[0], Constants.OUTPUT_PATH)
                LoggerUtils.writeLog("Success unpack: " + p1[0])
                doProgress("Patching manifest…")
                //ManifestPatcher.manifestPatch(Constants.MANIFEST_PATH)
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
        }
        if (Preferences.getEncryptResourcesBoolean()) {
            doProgress("Copying apk…")
            val rules = path + File.separator + "proguard-resources.json"
            if(!File(rules).exists()) {
                FileUtils.inputStreamAssets(getContext(), "proguard-resources.json", rules)
            }
            val tmpApk = Constants.RELEASE_PATH + File.separator + "app-temp.apk"
            val alignedApk = Constants.RELEASE_PATH + File.separator + "app-aligned.apk";

            if (FileUtils.copyFileStream(File(p1[0]), File(tmpApk))) {
                doProgress("Encrypting Resources…")
                AndResGuard.proguard2(
                    File(tmpApk),
                    File(path + "/output/" + MyAppInfo.getPackage() + "/"),
                    File(path),
                    MyAppInfo.getPackage()
                )
                LoggerUtils.writeLog("Encrypted Resources")
                SourceInfo.initialise("$path/output", mi!!)
                if (Preferences.getZipAlignerBoolean()) {
                    doProgress("Aligning Apk…")
                    if (ZipAlign.runProcess(tmpApk, alignedApk)) {
                        LoggerUtils.writeLog("Apk Aligned")
                        doProgress("Signing Apk…")
                        if (SignatureTool.sign(
                                context,
                                File(alignedApk),
                                File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                            )
                        ) {
                            LoggerUtils.writeLog("Apk Signed")
                            doProgress("Done")
                            t = true
                        }
                    }

                } else {
                    doProgress("Signing Apk…")
                    if (SignatureTool.sign(
                            context,
                            File(tmpApk),
                            File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                        )
                    ) {
                        LoggerUtils.writeLog("Apk Signed")
                        doProgress("Done")
                        t = true
                    }
                }
            }
        }
        if (Preferences.getSignApkBoolean()) {
            doProgress("Copying apk…")
            val tmpApk = Constants.RELEASE_PATH + File.separator + "app-temp.apk"
            val alignedApk = Constants.RELEASE_PATH + File.separator + "app-aligned.apk";

            if (FileUtils.copyFileStream(File(p1[0]), File(tmpApk))) {
                SourceInfo.initialise("$path/output", mi!!)
                if (Preferences.getZipAlignerBoolean()) {
                    doProgress("Aligning Apk…")
                    if (ZipAlign.runProcess(tmpApk, alignedApk)) {
                        LoggerUtils.writeLog("Apk Aligned")
                        doProgress("Signing Apk…")
                        if (SignatureTool.sign(
                                context,
                                File(alignedApk),
                                File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                            )
                        ) {
                            LoggerUtils.writeLog("Apk Signed")
                            doProgress("Done")
                            t = true
                        }
                    }
                } else {
                    doProgress("Signing Apk…")
                    if (SignatureTool.sign(
                            context,
                            File(tmpApk),
                            File(path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")
                        )
                    ) {
                        LoggerUtils.writeLog("Apk Signed")
                        doProgress("Done")
                        t = true
                    }
                }
            }
        }
        LoggerUtils.writeLog("Work time: " + (System.currentTimeMillis() - time))
        return@withContext t
    }

    private fun deviceInformation() {
        LoggerUtils.writeLog("************ DEVICE INFORMATION ***********")
        LoggerUtils.writeLog("Brand: " + Build.BRAND)
        LoggerUtils.writeLog("Device: " + Build.DEVICE)
        LoggerUtils.writeLog("Model: " + Build.MODEL)
        LoggerUtils.writeLog("Id: " + Build.ID)
        LoggerUtils.writeLog("\n************ FIRMWARE INFORMATION ***********")
        LoggerUtils.writeLog("SDK: " + VERSION.SDK_INT)
        LoggerUtils.writeLog("Release: " + VERSION.RELEASE)
        LoggerUtils.writeLog("Incremental: " + VERSION.INCREMENTAL)
    }

    private fun generateRandom() {
        Preferences.setPackageName(CommonUtils.generateRandomString(Constants.PACKAGE_NAME));
        Preferences.setFolderDexesName(CommonUtils.generateRandomString(Constants.DEX_DIR))
        Preferences.setPrefixDexesName(CommonUtils.generateRandomString(Constants.DEX_PREFIX));
        Preferences.setSuffixDexesName(CommonUtils.generateRandomString(Constants.DEX_SUFFIX))
    }

    private suspend fun doProgress(value: String?) = withContext(coroutineContext) {
        onProgressUpdate(value)
    }

    private fun onProgressUpdate(vararg values: String?) {
        protectLoadDialog!!.setTitle(values[0])
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
        return LinearLayout(ctx).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            orientation = LinearLayout.VERTICAL
            /*addView(TextView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(dp96, dp96)
                text = Preferences.getTempAxml()
                setPadding(dp16, dp16, dp16, dp16)
            })*/
            addView(ProgressBar(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(dp96, dp96)
                isIndeterminate = false
                setPadding(dp16, dp16, dp16, dp16)
            })
        }
    }
}