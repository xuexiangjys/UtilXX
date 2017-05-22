package com.xuexiang.util.common;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;

/**
 * java反射工具
 * 
 * @author xx
 */
public class JavaReflectUtil {
	/**
	 * 得到某个对象的属性
	 * 
	 * @param owner
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @return
	 * @throws Exception
	 */
	public static Object getProperty(Object owner, String fieldName) throws Exception {
		Class<?> ownerClass = owner.getClass();
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(owner);
		return property;
	}

	/**
	 * 得到某个类的静态属性
	 * 
	 * @param className
	 *            类名
	 * @param fieldName
	 *            属性名
	 * @return
	 * @throws Exception
	 */
	public static Object getStaticProperty(String className, String fieldName) throws Exception {
		Class<?> ownerClass = Class.forName(className);
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(ownerClass);
		return property;
	}

	/**
	 * 执行某对象的方法
	 * 
	 * @param owner
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数集合
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
		Class<?> ownerClass = owner.getClass();
		Class<?>[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}
		Method method = ownerClass.getMethod(methodName, argsClass);
		return method.invoke(owner, args);
	}

	/**
	 * 执行某个类的静态方法
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param args
	 *            参数集合
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
		Class<?> ownerClass = Class.forName(className);
		Class<?>[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}
		Method method = ownerClass.getMethod(methodName, argsClass);
		return method.invoke(null, args);
	}

	/**
	 * 新建实例
	 * 
	 * @param className
	 *            类名
	 * @param args
	 *            构造函数的参数集合
	 * @return
	 * @throws Exception
	 */
	public static Object newInstance(String className, Object[] args) throws Exception {
		Class<?> newoneClass = Class.forName(className);
		Class<?>[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}
		Constructor<?> cons = newoneClass.getConstructor(argsClass);
		return cons.newInstance(args);
	}

	/**
	 * 判断是否为某个类的实例
	 * 
	 * @param obj
	 *            对象
	 * @param cls
	 *            类
	 * @return
	 */
	public static boolean isInstance(Object obj, Class<?> cls) {
		return cls.isInstance(obj);
	}

	/**
	 * 得到数组中的某个元素
	 * 
	 * @param array
	 *            数组对象
	 * @param index
	 *            索引
	 * @return
	 */
	public static Object getByArray(Object array, int index) {
		return Array.get(array, index);
	}

	/**
	 * 得到某个类公共属性（public）的个数
	 * 
	 * @param className
	 *            类名
	 * @return
	 * @throws Exception
	 */
	public static int getPropertyNum(String className) throws Exception {
		Class<?> ownerClass = Class.forName(className);
		Field[] fields = ownerClass.getFields();
		return fields.length;
	}

	/**
	 * object toString的方法
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String toString(Object object) throws Exception {
		ArrayList<Field> list = new ArrayList<Field>();
		Class<?> clazz = object.getClass();
		list.addAll(Arrays.asList(clazz.getDeclaredFields()));// 得到自身的所有字段
		StringBuilder sb = new StringBuilder();
		while (clazz != Object.class) {
			clazz = clazz.getSuperclass();// 得到继承自父类的字段
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				int modifier = field.getModifiers();
				if (Modifier.isPublic(modifier) || Modifier.isProtected(modifier)) {
					list.add(field);
				}
			}
		}
		Field[] fields = list.toArray(new Field[list.size()]);
		for (Field field : fields) {
			String fieldName = field.getName();
			try {
				Object obj = field.get(object);
				sb.append(fieldName);
				sb.append("=");
				sb.append(obj);
				sb.append("\n");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * 获取activity的名称
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	public static String getActivityTag(Activity activity) throws Exception {
		return activity.getClass().getSimpleName();
	}

}
