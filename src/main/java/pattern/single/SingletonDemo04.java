package pattern.single;

/**
 * @author 张航铭
 *	静态内部类机制
 */
public class SingletonDemo04 {
	//私有化构造函数
	private SingletonDemo04() {
		
	}
	//声明，私有的静态成员变量
	private static SingletonDemo04 instance;
	//对外提供静态公有的方法后去单例对象
	public static SingletonDemo04 getInstance() {
		if (instance==null) {
			instance = Sigleton.getSigletonDemo04();
		}
		return instance;
	}
	private static class Sigleton{
		private static final SingletonDemo04 INSTANCE = new SingletonDemo04();
		public static SingletonDemo04 getSigletonDemo04() {
			
			return Sigleton.INSTANCE;
		}
	}
}
