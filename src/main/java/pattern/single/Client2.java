package pattern.single;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unchecked")
public class Client2 {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		SingletonDemo06 instance1 = SingletonDemo06.getInstance();
		SingletonDemo06 instance2 = SingletonDemo06.getInstance();
		System.out.println(instance1);
		System.out.println(instance2);
		System.out.println(instance1==instance2);
		//通过反射的方式直接调用是由构造器
//		Class<SingletonDemo06> clazz = (Class<SingletonDemo06>) Class.forName("single.SingletonDemo06");
//		Constructor<SingletonDemo06> c = clazz.getDeclaredConstructor(null);
//		c.setAccessible(true);
//		SingletonDemo06 s3 = c.newInstance();
//		SingletonDemo06 s4 = c.newInstance();
//		System.out.println(s3);
//		System.out.println(s4);
		//通过反序列化创建多例
		FileOutputStream fos = new FileOutputStream(new File("a.txt"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(instance1);
		oos.close();
		fos.close();
		FileInputStream fis = new FileInputStream(new File("a.txt"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		SingletonDemo06 instance3 = (SingletonDemo06) ois.readObject();
		System.out.println(instance3);
	}
}
