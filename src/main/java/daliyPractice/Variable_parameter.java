package daliyPractice;

/**
 * @author 张航铭
 * 可变参数。
 * 其实就是一种数组参数的简写形式。
 * 不用每一次都建立数组对象。
 * 
 * 可变参数一定定义在参数列表的最后边。
 */
public class Variable_parameter {
	public static void main(String[] args) {
		show(2,3,4);
	}
	public static void show(int...is) {
		for (int i : is) {
			System.out.println(i);
		}
	}
}
