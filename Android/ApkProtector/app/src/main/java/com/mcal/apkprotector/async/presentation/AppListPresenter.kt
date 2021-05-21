package com.mcal.apkprotector.async.presentation

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.mcal.apkprotector.App
import com.mcal.apkprotector.data.dto.applist.AppInteractor
import com.mcal.apkprotector.data.dto.applist.PackageInfoHolder
import com.mcal.apkprotector.utils.CommonUtils
import kotlinx.coroutines.*
import ru.svolf.melissa.sheet.SweetViewDialog
import java.lang.ref.WeakReference
import java.util.*
import kotlin.coroutines.CoroutineContext

class AppListPresenter : CoroutineScope {
    private var loaderJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + loaderJob

    private var packageLoadDialog: SweetViewDialog? = null
    private var interactor: AppInteractor? = null
    var context: WeakReference<Context>? = null

    private val dp16 = App.dpToPx(16)
    private val dp96 = App.dpToPx(96)

    fun cancel() {
        loaderJob.cancel()
    }

    fun execute() = launch {
        onPreExecute()
        val result = doInBackground()
        onPostExecute(result)
    }

    fun attach(context: Context, interactor: AppInteractor?) {
        this.context = WeakReference(context)
        this.interactor = interactor
    }

    private suspend fun doInBackground(): ArrayList<PackageInfoHolder?>? =
        withContext(Dispatchers.IO) {
            onProgressUpdate("Retrieving installed application")
            return@withContext getInstalledApps(context!!.get()!!)
        }

    private fun onPostExecute(AllPackages: ArrayList<PackageInfoHolder?>?) {
        interactor!!.setup(AllPackages)
        dismissProgressDialog()

    }

    private suspend fun doProgress(value: String?) = withContext(coroutineContext) {
        onProgressUpdate(value)
    }

    private suspend fun onPreExecute() = withContext(coroutineContext) {
        showProgressDialog()
        delay(5_000)
    }

    private suspend fun onProgressUpdate(vararg text: String?) = withContext(coroutineContext) {
        packageLoadDialog!!.setTitle(text[0])
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

    private fun showProgressDialog() {
        if (packageLoadDialog == null) {
            packageLoadDialog = SweetViewDialog(context?.get()!!).apply {
                setTitleTextSize(14f)
                setView(makeView(context))
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                setTitle("Loading installed applications...")
            }
        }
        packageLoadDialog!!.show()
    }

    private fun dismissProgressDialog() {
        if (packageLoadDialog != null && packageLoadDialog!!.isShowing) {
            packageLoadDialog!!.dismiss()
        }
    }

    private fun getInstalledApps(context: Context): ArrayList<PackageInfoHolder?>? {
        val res = ArrayList<PackageInfoHolder?>()
        val packages = context.packageManager.getInstalledPackages(0)
        val totalPackages = packages.size
        for (i in 0 until totalPackages) {
            val p = packages[i]
            if (!CommonUtils.isSystemPackage(p)) {
                var appInfo: ApplicationInfo? = null
                try {
                    appInfo = context.packageManager.getApplicationInfo(p.packageName, 0)
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }
                val count = i + 1
                val newInfo = PackageInfoHolder()
                newInfo.packageLabel =
                    p.applicationInfo.loadLabel(context.packageManager).toString()
                GlobalScope.launch {
                    doProgress("Loading application " + count + " of " + totalPackages + " (" + newInfo.packageLabel + ")")
                }
                newInfo.packageName = p.packageName
                newInfo.packageVersion = p.versionName
                if (appInfo != null) {
                    newInfo.packageFilePath = appInfo.publicSourceDir
                }
                newInfo.packageIcon = p.applicationInfo.loadIcon(context.packageManager)
                res.add(newInfo)
            }
        }
        val appNameComparator = Comparator { o1: PackageInfoHolder, o2: PackageInfoHolder ->
            o1.getPackageLabel().toLowerCase().compareTo(o2.getPackageLabel().toLowerCase())
        }
        Collections.sort(res, appNameComparator)
        return res
    }
}