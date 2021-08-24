package pattern.single;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 懒汉式单例模式
 * 防止反射和序列化漏洞
 */
public class SingletonDemo06 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//私有化构造函数
	private SingletonDemo06() {
		if (instance != null) {
			throw new RuntimeException("不能创建多个实例");
		}
	}
	//声明，私有的静态的成员变量
	private static SingletonDemo06 instance;
	//向外提供一个共有的静态的方法获取对象
	public static synchronized SingletonDemo06 getInstance() {
		if(instance==null) {		
			instance = new SingletonDemo06();
		}
		return instance;
	}
	
	/**
	 * 反序列化是，如果定义了readResolve()则直接返回此方法指定的对象。而不需要再创建对象
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return instance;
	}
}
