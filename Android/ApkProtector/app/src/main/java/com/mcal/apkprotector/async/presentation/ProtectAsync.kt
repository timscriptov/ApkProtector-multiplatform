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
import com.mcal.apkprotector.async.ProtectAsyncListener
import com.mcal.apkprotector.data.Preferences
import com.mcal.apkprotector.signer.SignatureTool
import com.mcal.apkprotector.task.*
import com.mcal.apkprotector.utils.*
import com.mcal.apkprotector.zipalign.ZipAlign
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.svolf.melissa.sheet.SweetViewDialog
import java.io.File
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
        val gen = File("$xpath/gen")
        if (!gen.exists()) {
            gen.mkdir()
        }
        val jar = File("$xpath/gen/jar")
        if (!jar.exists()) {
            jar.mkdir()
        }
        val output = File("$xpath/output")
        if (!output.exists()) {
            output.mkdir()
        }
        File(xpath).mkdirs()
        Utils.delete(
            ScopedStorage.getStorageDirectory()
                .toString() + File.separator + "ApkProtect" + File.separator + "Log.txt"
        )
        Utils.deleteFolderContent("$xpath/gen")
        Utils.deleteFolderContent("$xpath/output")
    }

    private fun onProgressUpdate(vararg values: String?) {
        protectLoadDialog!!.setTitle(values[0])
    }

    private fun onPostExecute(result: Boolean) {
        dismissProgressDialog()
        System.gc()
        Utils.deleteFolderContent("$xpath/gen")
        Utils.deleteFolderContent("$xpath/output")
        if (result) {
            listener.onCompleted()
        } else {
            val sourceDir =
                File(ScopedStorage.getStorageDirectory().absolutePath + "/ApkProtect/output/" + MyAppInfo.getPackage() + "")
            if (sourceDir.exists()) {
                Utils.deleteFolder(sourceDir)
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
            if (FileUtils.copyFileStream(File(p1[0]), File("$xpath/output/app.apk"))) {
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
            }
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
                    //if (com.mcal.apkprotector.utils.ZipAligner.fnAapt(context, "$xpath/output/app.apk", "$xpath/output/aligned.apk")) {
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
        /*if (Preferences.getEncryptStringsBoolean()) {
            doProgress("Copying apk…")
            File("$xpath/gen/").mkdir()
            ZipUtil.unpack(File(p1[0]), File("$xpath/gen/"));
            //if (UnZipApk.unZipIt(p1[0], "$xpath/gen/")) {
                // 3. Конвертация DEX в JAR
                for (file in File("$xpath/gen").listFiles()) {
                    if (file.name.endsWith(".dex")) {
                        doProgress("DEX - Dex2Jar…")
                        val jar = File("$xpath/gen/jar")
                        if (!jar.exists()) {
                            jar.mkdir()
                        }
                        //Dex2jar.from("$xpath/gen/" + file.name).to("$xpath/gen/jar/" + file.name.replace(".dex", ".jar"))
                        File("$xpath/gen/" + file.name).delete()
                    }
                }
                // 4. Шифрование строк в DEX
                for (file in File("$xpath/gen/jar").listFiles()) {
                    if (file.name.endsWith(".jar")) {
                        doProgress("DEX - Encrypting classes…")
                        File("$xpath/gen/classes/").mkdir()
                        UnZipApk.unZipIt("$xpath/gen/jar/" + file.name, "$xpath/gen/classes/")
                        StringFog.doFogClasses("$xpath/gen/classes/", path + "/output/" + MyAppInfo.getPackage())
                    }
                }
                if (FileCustomUtils.inputStreamAssets(context, "dexloader.jar", "$xpath/gen/jar/dexloader.jar")) {
                    // 4. Class2Dex
                    doProgress("DEX - Dexing classes…")
                    ZipUtil.unpack(File("$xpath/gen/jar/dexloader.jar"), File("$xpath/gen/classes/"))
                    DexedClasses.dexBuildClasses(File("$xpath/gen/classes/"), File("$xpath/gen/"))
                }
                Utils.deleteFolderContent("$xpath/gen/jar")
                Utils.deleteFolderContent("$xpath/gen/classes")
                doProgress("Building APK…")
                if (BuildApk.buildApk("$xpath/gen", "$xpath/output/unsigned.apk")) {
                    doProgress("Signing APK…")
                    SourceInfo.initialise("$path/output", mi!!)
                    if (ApkSigner.signApk("$xpath/output/unsigned.apk", path + "/output/" + MyAppInfo.getPackage() + "/" + MyAppInfo.getAppName() + ".apk")) {
                        doProgress("Done")
                        t = true
                    }
                }
            //}
        }*/
        return@withContext t
    }

    private fun renameAppClass(): Boolean {
        return try {
            if (Preferences.isOptimizeDexBoolean()) {
                if (FileCustomUtils.inputStreamAssets(
                        context,
                        "dexloader.dex",
                        "$xpath/gen/dexloader.dex"
                    )
                ) {
                    if (DexPatcher.dexPatch(context, "$xpath/gen/dexloader.dex")) {
                        DexOptimizer().init("$xpath/gen")
                    }
                }
            } else {
                if (FileCustomUtils.inputStreamAssets(
                        context,
                        "dexloader.dex",
                        "$xpath/gen/classes.dex"
                    )
                ) {
                    DexPatcher.dexPatch(context, "$xpath/gen/classes.dex")
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
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

    companion object {
        private const val TAG = "ApkProtector"
    }
}