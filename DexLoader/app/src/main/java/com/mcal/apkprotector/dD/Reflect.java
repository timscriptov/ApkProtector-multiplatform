/* Orginal file: Reflect.java
Java source was protected by the Android App: 'Protect Java source file'
Free version: https://play.google.com/store/apps/details?id=com.tth.JavaProtector
To get more support:  email to blueseateam.x@gmail.com
\com.mcal.apkprotector.utils*/
package com.mcal.apkprotector.dD;

//import java.lang.reflect.Field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//

//public class Reflect {
public class Reflect {

    //

    //public static Object invokeMethod(Class<?> clazz, Object obj, String methodName, Object[] args, Class<?>... parameterTypes) {
    public static Object invokeMethod(Class<?> clazz, Object obj, String methodName, Object[] args, Class<?>... parameterTypes) {
        //try {
        try {
            //

            //Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            //method.setAccessible(true); 
            method.setAccessible(true);
            //

            //return method.invoke(obj, args);
            return method.invoke(obj, args);
            //} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return null;
        return null;
        //}
    }

    //

    //public static Object invokeMethod(String className, Object obj, String methodName, Object[] args, Class<?>... parameterTypes) {
    public static Object invokeMethod(String className, Object obj, String methodName, Object[] args, Class<?>... parameterTypes) {
        //try {
        try {
            //

            //if (parameterTypes == null) {
            if (parameterTypes == null) {
                //parameterTypes = new Class[0];
                parameterTypes = new Class[0];
                //}
            }
            //if (args == null) {
            if (args == null) {
                //args = new Object[0];
                args = new Object[0];
                //}
            }
            //

            //Class<?> clazz = Class.forName(className);
            Class<?> clazz = Class.forName(className);
            //return invokeMethod(clazz, obj, methodName, args, parameterTypes);
            return invokeMethod(clazz, obj, methodName, args, parameterTypes);
            //} catch (ClassNotFoundException e) {
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return null;
        return null;
        //}
    }

    //

    //public static Object getFieldValue(Class<?> clazz, Object obj, String fieldName) {
    public static Object getFieldValue(Class<?> clazz, Object obj, String fieldName) {
        //try {
        try {
            //Field field = clazz.getDeclaredField(fieldName);
            Field field = clazz.getDeclaredField(fieldName);
            //field.setAccessible(true);
            field.setAccessible(true);
            //return field.get(obj);
            return field.get(obj);
            //} catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return null;
        return null;
        //}
    }

    //

    //public static Object getFieldValue(String className, Object obj, String fieldName) {
    public static Object getFieldValue(String className, Object obj, String fieldName) {
        //try {
        try {
            //Class<?> clazz = Class.forName(className);
            Class<?> clazz = Class.forName(className);
            //return getFieldValue(clazz, obj, fieldName);
            return getFieldValue(clazz, obj, fieldName);
            //} catch (ClassNotFoundException | IllegalArgumentException e) {
        } catch (ClassNotFoundException | IllegalArgumentException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return null;
        return null;
        //}
    }

    //

    //public static boolean setFieldValue(Class<?> clazz, Object obj, String fieldName, Object value) {
    public static boolean setFieldValue(Class<?> clazz, Object obj, String fieldName, Object value) {
        //try {
        try {
            //Field field = clazz.getDeclaredField(fieldName);
            Field field = clazz.getDeclaredField(fieldName);
            //field.setAccessible(true);
            field.setAccessible(true);
            //field.set(obj, value);
            field.set(obj, value);
            //return true;
            return true;
            //} catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return false;
        return false;
        //}
    }

    //

    //public static boolean setFieldValue(String className, Object obj, String fieldName, Object value) {
    public static boolean setFieldValue(String className, Object obj, String fieldName, Object value) {
        //try {
        try {
            //Class<?> clazz = Class.forName(className);
            Class<?> clazz = Class.forName(className);
            //setFieldValue(clazz, obj, fieldName, value);
            setFieldValue(clazz, obj, fieldName, value);
            //return true;
            return true;
            //} catch (ClassNotFoundException e) {
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //} catch (IllegalArgumentException e) {
        } catch (IllegalArgumentException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return false;
        return false;
        //}
    }

    //

    //public static void setFieldOjbect(String classname, String filedName, Object obj, Object filedVaule) {
    public static void setFieldOjbect(String classname, String filedName, Object obj, Object filedVaule) {
        //try {
        try {
            //Class<?> obj_class = Class.forName(classname);
            Class<?> obj_class = Class.forName(classname);
            //Field field = obj_class.getDeclaredField(filedName);
            Field field = obj_class.getDeclaredField(filedName);
            //field.setAccessible(true);
            field.setAccessible(true);
            //field.set(obj, filedVaule);
            field.set(obj, filedVaule);
            //} catch (SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
        } catch (SecurityException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //}
    }

    //

    //public static Object getFieldOjbect(String class_name, Object obj, String filedName) {
    public static Object getFieldOjbect(String class_name, Object obj, String filedName) {
        //try {
        try {
            //Class<?> obj_class = Class.forName(class_name);
            Class<?> obj_class = Class.forName(class_name);
            //Field field = obj_class.getDeclaredField(filedName);
            Field field = obj_class.getDeclaredField(filedName);
            //field.setAccessible(true);
            field.setAccessible(true);
            //return field.get(obj);
            return field.get(obj);
            //} catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException | ClassNotFoundException e) {
        } catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException | ClassNotFoundException e) {
            //e.printStackTrace();
            e.printStackTrace();
            //}
        }
        //return null;
        return null;
        //}
    }
//}
}
//
  