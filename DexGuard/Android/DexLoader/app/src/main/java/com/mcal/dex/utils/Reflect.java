package com.mcal.dex.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflect {

    /**
     * 反射获得 指定对象(当前-》父类-》父类...)中的 成员属性
     *
     * @param instance
     * @param name
     * @return
     * @throws NoSuchFieldException
     */
    @NotNull
    public static Field findField(@NotNull Object instance, String name) throws NoSuchFieldException {
        Class clazz = instance.getClass();
        //反射获得
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(name);
                //如果无法访问 设置为可访问
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                return field;
            } catch (NoSuchFieldException e) {
                //如果找不到往父类找
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field " + name + " not found in " + instance.getClass());
    }


    /**
     * 反射获得 指定对象(当前-》父类-》父类...)中的 函数
     *
     * @param instance
     * @param name
     * @param parameterTypes
     * @return
     * @throws NoSuchMethodException
     */
    @NotNull
    public static Method findMethod(@NotNull Object instance, String name, Class... parameterTypes)
            throws NoSuchMethodException {
        Class clazz = instance.getClass();
        while (clazz != null) {
            try {
                Method method = clazz.getDeclaredMethod(name, parameterTypes);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                return method;
            } catch (NoSuchMethodException e) {
                //如果找不到往父类找
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchMethodException("Method " + name + " with parameters " + Arrays.asList
                (parameterTypes) + " not found in " + instance.getClass());
    }

    /**
     * 调用类或对象的方法并返回结果
     *
     * @param clazz          类
     * @param methodName     方法名
     * @param obj            调用该方法的对象，如果是静态方法则传null
     * @param args           参数，如果没有则传null
     * @param parameterTypes 方法参数类型的class，如果没有则传null
     * @return 调用结果
     */
    @Nullable
    public static Object invokeMethod(@NotNull Class<?> clazz, Object obj, String methodName, Object[] args, Class<?>... parameterTypes) {
        try {
            // 反射类指定方法
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true); // 暴力反射
            // 调用方法并返回结果
            return method.invoke(obj, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 调用类或对象的方法并返回结果
     *
     * @param className      类名
     * @param methodName     方法名
     * @param obj            调用该方法的对象，如果是静态方法则传null
     * @param args           参数，如果没有则传null
     * @param parameterTypes 方法参数类型的class，如果没有则传null
     * @return 调用结果
     */
    @Nullable
    public static Object invokeMethod(String className, Object obj, String methodName, Object[] args, Class<?>... parameterTypes) {
        try {
            // 防止空指针错误
            if (parameterTypes == null) {
                parameterTypes = new Class[0];
            }
            if (args == null) {
                args = new Object[0];
            }
            // 加载类的字节码
            Class<?> clazz = Class.forName(className);
            return invokeMethod(clazz, obj, methodName, args, parameterTypes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取对象或类某个字段的值
     *
     * @param clazz     类
     * @param obj       对象，如果是静态字段则传null
     * @param fieldName 字段名称
     * @return 字段的值
     */
    @Nullable
    public static Object getFieldValue(@NotNull Class<?> clazz, Object obj, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取对象或类某个字段的值
     *
     * @param className 类名
     * @param obj       对象，如果是静态字段则传null
     * @param fieldName 字段名称
     * @return 字段的值
     */
    @Nullable
    public static Object getFieldValue(String className, Object obj, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            return getFieldValue(clazz, obj, fieldName);
        } catch (ClassNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置对象或类某个字段的值
     *
     * @param clazz     类
     * @param obj       对象，如果是静态字段则传null
     * @param fieldName 字段名称
     * @param value     字段值
     * @return 是否设置成功
     */
    public static boolean setFieldValue(@NotNull Class<?> clazz, Object obj, String fieldName, Object value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
            return true;
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置对象或类某个字段的值
     *
     * @param className 类名
     * @param obj       对象，如果是静态字段则传null
     * @param fieldName 字段名称
     * @param value     字段值
     * @return 是否设置成功
     */
    public static boolean setFieldValue(String className, Object obj, String fieldName, Object value) {
        try {
            Class<?> clazz = Class.forName(className);
            setFieldValue(clazz, obj, fieldName, value);
            return true;
        } catch (ClassNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据类名实例化一个对象
     *
     * @param className 类名
     * @return 对象实例，如果实例化失败返回null
     */
    @Nullable
    public static Object newInstance(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}