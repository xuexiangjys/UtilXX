package com.xuexiang.util.common.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.util.Log;

/**
 * java反射工具
 * 
 * @author xx
 */
public class JavaReflectUtil {
	private static final String TAG = "JavaReflectUtil";

	/**
	 * 得到某个对象的属性
	 * 
	 * @param clazz
	 *            类
	 * @param target
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @return
	 * @throws Exception
	 */
	public static Object getField(Class clazz, Object target, String fieldName) throws Exception {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		return field.get(target);
	}

	public static Object getFieldNoException(Class clazz, Object target, String fieldName) {
		try {
			return JavaReflectUtil.getField(clazz, target, fieldName);
		} catch (Exception e) {
			// ignored.
		}
		return null;
	}

	/**
	 * 设置某个对象的属性
	 * 
	 * @param clazz
	 *            类
	 * @param target
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @param value
	 *            设置的值
	 * @throws Exception
	 */
	public static void setField(Class clazz, Object target, String fieldName, Object value) throws Exception {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(target, value);
	}

	public static void setFieldNoException(Class clazz, Object target, String fieldName, Object value) {
		try {
			JavaReflectUtil.setField(clazz, target, fieldName, value);
		} catch (Exception e) {
			// ignored.
		}
	}

	/**
	 * 执行某一方法
	 * 
	 * @param clazz
	 *            类
	 * @param target
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param args
	 *            方法的参数
	 * @return
	 * @throws Exception
	 */
	public static Object invoke(Class clazz, Object target, String methodName, Object... args) throws Exception {
		Class[] parameterTypes = getParamterTypes(args);
		return invoke(clazz, target, methodName, parameterTypes, args);
	}

	/**
	 * 获取参数的类型
	 * @param args 参数
	 * @return
	 */
	private static Class[] getParamterTypes(Object... args) {
		Class[] parameterTypes = null;
		if (args != null) {
			parameterTypes = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				parameterTypes[i] = args[i].getClass();
			}
		}
		return parameterTypes;
	}

	public static Object invokeNoException(Class clazz, Object target, String methodName, Object... args) {
		try {
			return invoke(clazz, target, methodName, args);
		} catch (Exception e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Object invoke(Class clazz, Object target, String methodName, Class[] parameterTypes, Object... args) throws Exception {
		Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
		method.setAccessible(true);
		return method.invoke(target, args);
	}

	public static Object invokeNoException(Class clazz, Object target, String methodName, Class[] parameterTypes, Object... args) {
		try {
			return invoke(clazz, target, methodName, parameterTypes, args);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 执行构造方法
	 * @param clazz 类
	 * @param args 参数
	 * @return
	 * @throws Exception
	 */
	public static Object invokeConstructor(Class clazz, Object... args) throws Exception {
		Class[] parameterTypes = getParamterTypes(args);
		return invokeConstructor(clazz, parameterTypes, args);
	}
	
	public static Object invokeConstructorNoException(Class clazz, Object... args) {
		try {
			return invokeConstructor(clazz, args);
		} catch (Exception e) {
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Object invokeConstructor(Class clazz, Class[] parameterTypes, Object... args) throws Exception {
		Constructor constructor = clazz.getDeclaredConstructor(parameterTypes);
		constructor.setAccessible(true);
		return constructor.newInstance(args);
	}
	
	public static Object invokeConstructorNoException(Class clazz, Class[] parameterTypes, Object... args) {
		try {
			return invokeConstructor(clazz, parameterTypes, args);
		} catch (Exception e) {
		}
		return null;
	}

	//================================================================================================//
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
		Field field = ownerClass.getDeclaredField(fieldName);
		field.setAccessible(true);
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
		Field field = ownerClass.getDeclaredField(fieldName);
		field.setAccessible(true);
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
		Method method = ownerClass.getDeclaredMethod(methodName, argsClass);
		method.setAccessible(true);
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
		Method method = ownerClass.getDeclaredMethod(methodName, argsClass);
		method.setAccessible(true);
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
		Constructor<?> cons = newoneClass.getDeclaredConstructor(argsClass);
		cons.setAccessible(true);
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
	public static int getPublicPropertyNum(String className) throws Exception {
		Class<?> ownerClass = Class.forName(className);
		Field[] fields = ownerClass.getFields();
		return fields.length;
	}
	
	/**
	 * 得到某个类所有属性的个数
	 * 
	 * @param className
	 *            类名
	 * @return
	 * @throws Exception
	 */
	public static int getAllPropertyNum(String className) throws Exception {
		Class<?> ownerClass = Class.forName(className);
		Field[] fields = ownerClass.getDeclaredFields();
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

	/**
	 * 获取类里面的所在字段
	 */
	public static Field[] getFields(Class clazz) {
		Field[] fields = null;
		fields = clazz.getDeclaredFields();
		if (fields == null || fields.length == 0) {
			Class superClazz = clazz.getSuperclass();
			if (superClazz != null) {
				fields = getFields(superClazz);
			}
		}
		return fields;
	}

	/**
	 * 获取所有字段，包括父类的字段
	 */
	public static List<Field> getAllFields(Class clazz) {
		List<Field> fields = new ArrayList<>();
		Class personClazz = clazz.getSuperclass();
		if (personClazz != null) {
			Collections.addAll(fields, personClazz.getDeclaredFields());
		}
		Collections.addAll(fields, clazz.getDeclaredFields());
		return fields;
	}

	/**
	 * 获取类里面的指定对象，如果该类没有则从父类查询
	 */
	public static Field getField(Class clazz, String name) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			try {
				field = clazz.getField(name);
			} catch (NoSuchFieldException e1) {
				if (clazz.getSuperclass() == null) {
					return field;
				} else {
					field = getField(clazz.getSuperclass(), name);
				}
			}
		}
		if (field != null) {
			field.setAccessible(true);
		}
		return field;
	}

	/**
	 * 利用递归找一个类的指定方法，如果找不到，去父亲里面找直到最上层Object对象为止。
	 * 
	 * @param clazz
	 *            目标类
	 * @param methodName
	 *            方法名
	 * @param params
	 *            方法参数类型数组
	 * @return 方法对象
	 */
	public static Method getMethod(Class clazz, String methodName, final Class<?>... params) {
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName, params);
		} catch (NoSuchMethodException e) {
			try {
				method = clazz.getMethod(methodName, params);
			} catch (NoSuchMethodException ex) {
				if (clazz.getSuperclass() == null) {
					Log.e(TAG, "无法找到" + methodName + "方法");
					return method;
				} else {
					method = getMethod(clazz.getSuperclass(), methodName, params);
				}
			}
		}
		if (method != null) {
			method.setAccessible(true);
		}
		return method;
	}

}
