package pattern.single;

/**
 * 饿汉式单例模式
 * 缺点：实例对象会提前加载出来
 * 有点：线程安全，在多线程并发访问的情况下不会创建多个对象
 */
public class SingletonDemo01 {
	//声明一个静态的成员变量，并初始化
	private static SingletonDemo01 instance = new SingletonDemo01();
	//私有化构造函数
	private SingletonDemo01() {}
	//提供一个公有的静态的方法获取这个对象
	public static SingletonDemo01 getInstance() {
		return instance;
	}
	
}
