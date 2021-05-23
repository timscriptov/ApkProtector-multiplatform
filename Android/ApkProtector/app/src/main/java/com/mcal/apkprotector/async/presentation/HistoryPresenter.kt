package com.mcal.apkprotector.async.presentation

import com.mcal.apkprotector.activities.HomeActivity
import com.mcal.apkprotector.utils.SourceInfo
import com.mcal.apkprotector.utils.Utils
import kotlinx.coroutines.*
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class HistoryPresenter : CoroutineScope {

    private var loaderJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + loaderJob

    fun cancel() {
        loaderJob.cancel()
    }

    fun execute(path: String) = launch {
        onPreExecute()
        val result =
            doInBackground(path) // runs in background thread without blocking the Main Thread
        onPostExecute(result)
    }

    private var activityWeakReference: WeakReference<HomeActivity>? = null

    fun attach(activity: HomeActivity) {
        activityWeakReference = WeakReference(activity)
    }

    private suspend fun doInBackground(vararg params: String): ArrayList<SourceInfo?>? =
        withContext(Dispatchers.IO) {
            val historyItems: ArrayList<SourceInfo?> = ArrayList()
            val showJavaDir = File(params[0])
            showJavaDir.mkdirs()
            val nomedia = File(showJavaDir, ".nomedia")
            if (!nomedia.exists() || !nomedia.isFile) {
                try {
                    nomedia.createNewFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            val dir = File(params[0] + "/output")
            if (dir.exists()) {
                val files = dir.listFiles()
                if (files != null && files.isNotEmpty()) for (file in files) {
                    if (Utils.sourceExists(file)) {
                        historyItems.add(0, Utils.getSourceInfoFromSourcePath(file))
                    } else {
                        try {
                            if (file.exists()) {
                                if (file.isDirectory) {
                                    FileUtils.deleteDirectory(file)
                                } else {
                                    file.delete()
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        if (file.exists() && !file.isDirectory) {
                            file.delete()
                        }
                    }
                }
            }
            val AppNameComparator = Comparator { o1: SourceInfo, o2: SourceInfo ->
                o1.packageLabel.toLowerCase().compareTo(o2.packageLabel.toLowerCase())
            }
            Collections.sort(historyItems, AppNameComparator)
            return@withContext historyItems
        }

    private suspend fun onPostExecute(AllPackages: List<SourceInfo?>?) =
        withContext(coroutineContext) {
            activityWeakReference!!.get()!!.setupList(AllPackages!!)
        }

    private suspend fun onPreExecute() = withContext(coroutineContext) {
    }
}