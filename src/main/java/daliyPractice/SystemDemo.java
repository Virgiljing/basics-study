package daliyPractice;

import java.util.Properties;
import java.util.Set;

/**
 * @author 张航铭
 *	system的介绍
 */
public class SystemDemo {
	public static void main(String[] args) {
		//因为Properties是Hashtable的子类，也就是Map集合的一个子类对象。
		Properties properties = System.getProperties();
		//在系统属性中自定义一些信息
		System.setProperty("myKey", "myValue");
		//遍历系统属性
		Set<Object> keySet = properties.keySet();
		for (Object object : keySet) {
			String value = (String) properties.get(object);
			System.out.println(object+"::"+value);
		}
		//获得指定属性信息
		String value = System.getProperty("os.name");
		System.out.println("value"+"----"+value);
		
	}
}
