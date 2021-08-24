package lambda;

/**
 * @author 张航铭
 *	泛型：JDK1.5版本以后出现的新特性。用于解决安全问题，是一个安全机制。
 *	好处
 *  1.将运行时期出现的问题ClassCastException，转移到编译时期。
 *      方便于程序员解决问题。让运行事情问题减少，安全。
 *  2.避免了强制类型转换的麻烦
 *  
 *  
 */
public class GenericDemo {
	public static void main(String[] args) {
		Tool<Worker> t = new Tool<>();
		Worker e = t.getE();
		System.out.println(e);
		
		System.out.println("----------------");
		
		Demo<String> d = new Demo<>();
		d.show("aaaa");
		d.print("bbbb");
		
		Demo1 d1 = new Demo1();
		d1.show("haha");
		d1.print(4);
		
		System.out.println("-------------------");
		Demo2<String> d2 = new Demo2<>();
		d2.show("ssss");
		d2.print("aa");
		d2.print(3);
		
	}
}

class Worker{}


/**
 * @author 张航铭
 *
 * @param <QQ>
 * 泛型类。
 * 放操作的应用数据类型不确定的时候
 * 早期定义Object来完成扩展
 * 现在定义泛型类来完成扩展
 * 
 */
class Tool<QQ>{
	private QQ qq;

	public QQ getE() {
		return qq;
	}

	public void setE(QQ qq) {
		this.qq = qq;
	}
	
}

/**
 * @author 张航铭
 *
 * @param <T>
 * 
 * 泛型类定义的泛型，在整个类中有效。如果被方法使用，
 * 那么泛型类的对象明确操作的具体类型后，所有要操作的类型就已经固定了
 * 
 * 为了让不同方法操作不同类型，而且类型还不确定。
 * 可以将泛型定义在方法上。
 */
class Demo<T>{
	public void show(T t) {
		System.out.println("show:"+t);
	}
	public void print(T t) {
		System.out.println("print:"+t);
	}
}

class Demo1{
	public <T> void show(T t) {
		System.out.println("show:"+t);
	}
	public <Q> void print(Q q) {
		System.out.println("print:"+q);
	}
}

class Demo2<T>{
	public void show(T t) {
		System.out.println("show:"+t);
	}
	public <Q> void print(Q q) {
		System.out.println("print:"+q);
	}
}

/**
 * 特殊之处：
 * 静态方法不可以访问类上的放行
 */

class Demo3<T>{
	public void show(T t) {
		System.out.println("show:"+t);
	}
	public <Q> void print(Q q) {
		System.out.println("print:"+q);
	}
	public static <M> void run(M m) {
		System.out.println("run:"+m);
	}
}

/**
 * @author 张航铭
 *
 * @param <T>
 * 
 * 泛型定义到接口上
 */
interface Inter<T>{
	void show(T t);
}

class InterImpl implements Inter<String>{

	@Override
	public void show(String t) {
		System.out.println("show:"+t);
	}
	
}

class InterImpl1<T> implements Inter<T>{

	@Override
	public void show(T t) {
		System.out.println("show:"+t);
	}
	//声明子类具体类型
	//声明子类泛型类型
	//擦出  -->继承，实现不指定类型，使用时不指定类型
	//子类大于等于父类
	//泛型没有多态
	
	
}