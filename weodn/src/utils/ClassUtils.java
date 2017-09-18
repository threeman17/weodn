package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Class工具类.
 * @author xujie
 *
 */
public class ClassUtils {
	// 1.实例化指定的类
	/**
	 * 实例化指定的类.<br>
	 * 
	 * <style> .code{color:383} </style>
	 * 
	 * 代码示例:
	 * 
	 * <pre>
	 * 	<span class="code">//User user=new User();</span>
	 * 	User user=ClassUtils.newInstance(User.class);  				
	 * 	<span class="code">//User user=new User("admin","123");</span>
	 * 	User user=ClassUtils.newInstance(User.class,"admin","123");
	 * </pre>
	 * 
	 * @param taget
	 *            需要实例化的类.
	 * @param objects
	 *            构造方法参数列表.
	 * @return	返回实例化后的实例.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T newInstance(Class<T> taget, Object... objects) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?>[] parameterTypes = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			parameterTypes[i] = objects[i].getClass();
		}
		Constructor<T> constructor = taget.getConstructor(parameterTypes);
		return constructor.newInstance(objects);
	}

	/**
	 * 实例化指定名称的类.<br>
	 * 
	 * <style> .code{color:383} </style>
	 * 
	 * 代码示例:
	 * 
	 * <pre>
	 * 	<span class="code">//User user=new User();</span>
	 * 	User user=ClassUtils.newInstance("com.jason.User");  				
	 * 	<span class="code">//User user=new User("admin","123");</span>
	 * 	User user=ClassUtils.newInstance("com.jason.User","admin","123");
	 * </pre>
	 * 
	 * @param className
	 *            待实例化的类的名称
	 * @param objects
	 *            构造方法参数列表
	 * @return 返回实例化后的实例.
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className, Object... objects)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> forName = Class.forName(className);
		return (T) newInstance(forName, objects);
	}

	// 2.给指定属性设置值
	/**
	 * 给指定的实例中的指定属性设置值
	 * 
	 * @param obj
	 *            实例
	 * @param fieldName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void set(Object obj, String fieldName, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(obj, value);
	}

	// 3.获取指定的属性值
	/**
	 * 获取实例中指定属性的值
	 * 
	 * @param obj
	 *            实例
	 * @param fieldName
	 *            属性名称
	 * @return	返回属性的值.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Object obj, String fieldName)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return (T) field.get(obj);
	}
	/**
	 * 获取类中指定静态属性的值
	 * 
	 * @param obj
	 *            实例
	 * @param fieldName
	 *            属性名称
	 * @return	返回属性的值.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Class<?> type, String fieldName)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field field = type.getDeclaredField(fieldName);
		field.setAccessible(true);
		return (T) field.get(null);
	}

	// 4.执行指定的方法
	/**
	 * 执行实例中指定的方法
	 * 
	 * @param obj
	 *            实例.
	 * @param methodName
	 *            方法名称.
	 * @param objects
	 *            方法参数列表
	 * @return	返回方法执行后的返回值.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T invoke(Object obj, String methodName, Object... objects) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?>[] parameterTypes = new Class<?>[objects.length];
		for (int i = 0; i < objects.length; i++) {
			parameterTypes[i] = objects[i].getClass();
		}
		Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
		return (T) method.invoke(obj, objects);
	}

	// 5.获取指定类中的属性名称列表
	/**
	 * 获取指定类中的属性名称列表.
	 * 
	 * @param cla
	 *            待获取的类.
	 * @return	返回参数名称列表
	 */
	public static List<String> getFieldNames(Class<?> cla) {
		Field[] fields = cla.getDeclaredFields();
		if (fields == null || fields.length < 1) {
			return Collections.emptyList();
		}
		List<String> list = new ArrayList<>();
		for (Field field : fields) {
			list.add(field.getName());
		}
		return list;
	}

	// 6.检查指定的属性在类中是否存在
	/**
	 * 检测指定属性在类中是否存在
	 * 
	 * @param cla
	 *            待对比的类.
	 * @param fieldName
	 *            属性名称.
	 * @return	{@code true:存在,false:不存在}
	 */
	public static boolean isFiled(Class<?> cla, String fieldName) {
		List<String> list = getFieldNames(cla);
		for (String name : list) {
			if (name.equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		// User user = newInstance("com.jason.bean.User", "admin","123");
		// System.out.println(user);
	}
}
