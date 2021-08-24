package pattern.single;

/**
 * @author 张航铭
 *	双重锁检测机制
 */
public class SingletonDemo03 {
	//私有化构造函数
	private SingletonDemo03() {}
	//声明私有静态成员变量
	private static SingletonDemo03 instance;
	//对外提供公有静态的方法获取对象
	public static SingletonDemo03 getInstance() {
		if (instance==null) {
			synchronized (SingletonDemo03.class) {
				if (instance==null) {
					instance = new SingletonDemo03();
				}
			}
		}
		return instance;
	}
}
