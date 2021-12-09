package com.oscar0812.obfuscation;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

import brut.androlib.Androlib;
import brut.androlib.ApkOptions;
import brut.androlib.meta.MetaInfo;
import brut.util.Logger;

public class BuildTask extends AsyncTask<File, CharSequence, Boolean> implements Logger
{
    public File resultFile;
    public String mOutputApk;

    public BuildTask(String ouputApk)
    {
        mOutputApk = ouputApk;
    }

    @Override
    protected Boolean doInBackground(File[] p1)
    {
        boolean success = true;
        for (File file : p1)
        {
            if (!process(file, mOutputApk))
                success = false;
        }
        return success;
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
    }

    @Override
    protected void onProgressUpdate(CharSequence... values)
    {
    }

    @Override
    public void info(int id, Object... args)
    {
    }

    @Override
    public void warning(int id, Object... args)
    {
    }

    @Override
    public void fine(int id, Object... args)
    {
        // publishProgress(String.format("F:%s\n",getText(id, args)));
    }

    @Override
    public void text(int id, Object... args)
    {
    }

    @Override
    public void error(int id, Object... args)
    {
    }

    @Override
    public void log(Level level, String format, Throwable ex)
    {
        char ch = level.getName().charAt(0);
        String fmt = "%c: %s";
        log(fmt, ch, ex);
    }

    private void log(String fmt, char ch, Throwable ex)
    {
        log(fmt, ch, ex.getCause());
    }


    protected boolean process(File f, String output) {
        ApkOptions options = ApkOptions.INSTANCE;
        Androlib androlib = new Androlib(options);
        try {
            File tmp = File.createTempFile("APKTOOL", null);
            try {
                MetaInfo meta = androlib.build(f, tmp);
                output = f.getName().replace(".apk", "_unsigned.apk");
                File buildApkPath = new File(output);
                if (!buildApkPath.exists() && !buildApkPath.mkdirs()) {
                    return false;
                }
                File out = new File(buildApkPath, output);
                int min;
                if (meta.sdkInfo != null)
                    min = Integer.parseInt(Objects.requireNonNull(meta.sdkInfo.get("minSdkVersion")));
                else
                    min = 14;
            } finally {
                FileUtils.deleteDirectory(new File(f.getAbsolutePath() + "/build"));
                tmp.delete();
            }
            return true;
        } catch (Exception e) {
            log(Level.WARNING, e.getMessage(), e);
            return false;
        }
    }

    public void setResult(File f)
    {
        resultFile = f;
    }

    public void setResult(String f)
    {
        try
        {
            resultFile = new File(f);
        }
        catch (Exception e)
        {
            log(Level.SEVERE, "Result file failed", e);
        }
    }
}