package pattern.single;

/**
 * 懒汉式单例模式
 *	有点：不会提加载实例对象
 *	缺点：线程不安全，加锁可以解决
 *	加锁系统消耗资源
 */
public class SingletonDemo02 {
	//私有化构造函数
	private SingletonDemo02() {}
	//声明，私有的静态的成员变量
	private static SingletonDemo02 instance;
	//向外提供一个共有的静态的方法获取对象
	public static synchronized SingletonDemo02 getInstance() {
		if(instance==null) {		
			instance = new SingletonDemo02();
		}
		return instance;
	}
}
