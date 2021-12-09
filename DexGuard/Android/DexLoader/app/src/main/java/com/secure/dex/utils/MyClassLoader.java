package com.secure.dex.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyClassLoader {
    private static final String TAG = "MyClassLoader";
    @SuppressLint("StaticFieldLeak")
    public static Context mContext;
    private static List<File> mDexFiles = new ArrayList<>();
    File mDexDir;
    File mOptDir;

    public MyClassLoader(Context context, List<File> dexFiles, File dexDir, File optDir) {
        mContext = context;
        mDexFiles = dexFiles;
        mDexDir = dexDir;
        mOptDir = optDir;
    }

    private static void expandFieldArray(Object instance, Object [] extraElements) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field jlrField = Reflect.findField(instance, "dexElements");
        Object[] original = (Object[]) jlrField.get(instance);
        Object[] combined = (Object[]) Array.newInstance(original.getClass().getComponentType(), original.length + extraElements.length);
        System.arraycopy(original, 0, combined, 0, original.length);
        System.arraycopy(extraElements, 0, combined, original.length, extraElements.length);
        jlrField.set(instance, combined);
    }

    public void loadDexByVersion() throws Exception {
        ClassLoader classLoader = mContext.getClassLoader();
        //如果大于api 23，即6.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            Field pathListField = Reflect.findField(classLoader, "pathList");
            Object pathList = pathListField.get(classLoader);

            Field dexElementsField = Reflect.findField(pathList, "dexElements");
            Object[] dexElements = (Object[]) dexElementsField.get(pathList);

            Method makeDexElements = Reflect.findMethod(pathList, "makePathElements", List.class, File.class, List.class);

            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            Object[] addElements = (Object[]) makeDexElements.invoke(pathList, mDexFiles, mOptDir, suppressedExceptions);

            //合并数组
            Object[] newElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), dexElements.length + addElements.length);
            System.arraycopy(dexElements, 0, newElements, 0, dexElements.length);
            System.arraycopy(addElements, 0, newElements, dexElements.length, addElements.length);

            //替换classloader中的element数组
            dexElementsField.set(pathList, newElements);
            return;
        }

        //5.0 - 6.0 api 21-22
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            Field pathListField = Reflect.findField(classLoader, "pathList");
            Object dexPathList = pathListField.get(classLoader);

            ArrayList<IOException> suppressedExceptions = new ArrayList<>();
            expandFieldArray(dexPathList, v19.makeDexElements(dexPathList, new ArrayList<>(mDexFiles), mOptDir, suppressedExceptions));
            if (suppressedExceptions.size() > 0) {
                for (IOException e : suppressedExceptions) {
                    Log.w(TAG, "Exception in makeDexElement", e);
                }
                Field suppressedExceptionsField =
                        Reflect.findField(classLoader, "dexElementsSuppressedExceptions");
                IOException[] dexElementsSuppressedExceptions =
                        (IOException[]) suppressedExceptionsField.get(classLoader);

                if (dexElementsSuppressedExceptions == null) {
                    dexElementsSuppressedExceptions =
                            suppressedExceptions.toArray(
                                    new IOException[0]);
                } else {
                    IOException[] combined =
                            new IOException[suppressedExceptions.size() +
                                    dexElementsSuppressedExceptions.length];
                    suppressedExceptions.toArray(combined);
                    System.arraycopy(dexElementsSuppressedExceptions, 0, combined,
                            suppressedExceptions.size(), dexElementsSuppressedExceptions.length);
                    dexElementsSuppressedExceptions = combined;
                }

                suppressedExceptionsField.set(classLoader, dexElementsSuppressedExceptions);
            }
            return;
        }

        //4.4 api 19
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {

            Field pathListField = Reflect.findField(classLoader, "pathList");
            Object dexPathList = pathListField.get(classLoader);

            ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
            expandFieldArray(dexPathList, v19.makeDexElements(dexPathList,
                    new ArrayList<>(mDexFiles), mOptDir,
                    suppressedExceptions));
            if (suppressedExceptions.size() > 0) {
                for (IOException e : suppressedExceptions) {
                    Log.w(TAG, "Exception in makeDexElement", e);
                }
                Field suppressedExceptionsField =
                        Reflect.findField(classLoader, "dexElementsSuppressedExceptions");
                IOException[] dexElementsSuppressedExceptions =
                        (IOException[]) suppressedExceptionsField.get(classLoader);

                if (dexElementsSuppressedExceptions == null) {
                    dexElementsSuppressedExceptions =
                            suppressedExceptions.toArray(
                                    new IOException[0]);
                } else {
                    IOException[] combined =
                            new IOException[suppressedExceptions.size() +
                                    dexElementsSuppressedExceptions.length];
                    suppressedExceptions.toArray(combined);
                    System.arraycopy(dexElementsSuppressedExceptions, 0, combined,
                            suppressedExceptions.size(), dexElementsSuppressedExceptions.length);
                    dexElementsSuppressedExceptions = combined;
                }

                suppressedExceptionsField.set(classLoader, dexElementsSuppressedExceptions);
            }
            return;
        }

        //如果api大于等于14，小于等于18，即4.0-4.3
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {

            Field pathListField = Reflect.findField(classLoader, "pathList");
            Object dexPathList = pathListField.get(classLoader);

            expandFieldArray(dexPathList, v14_18.makeDexElements(dexPathList, new ArrayList<>(mDexFiles), mOptDir));
        }
    }

    private static class v19 {
        private static Object[] makeDexElements(Object dexPathList, ArrayList<File> files, File optimizedDirectory, ArrayList<IOException> suppressedExceptions)
                throws IllegalAccessException, InvocationTargetException,
                NoSuchMethodException {
            Method makeDexElements = Reflect.findMethod(dexPathList, "makeDexElements", ArrayList.class, File.class, ArrayList.class);

            return (Object[]) makeDexElements.invoke(dexPathList, files, optimizedDirectory, suppressedExceptions);
        }
    }

    private static class v14_18 {
        private static Object[] makeDexElements(
                Object dexPathList, ArrayList<File> files, File optimizedDirectory)
                throws IllegalAccessException, InvocationTargetException,
                NoSuchMethodException {
            Method makeDexElements =
                    Reflect.findMethod(dexPathList, "makeDexElements", ArrayList.class, File.class);

            return (Object[]) makeDexElements.invoke(dexPathList, files, optimizedDirectory);
        }
    }
}
