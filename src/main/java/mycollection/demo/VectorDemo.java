package mycollection.demo;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author 张航铭
 * 枚举就是Vector特有的取出方式。
 * 枚举和迭代器一样
 * 因为枚	举名字过长
 * 被迭代器取代了
 */
public class VectorDemo {
	public static void main(String[] args) {
		Vector<String> v = new Vector<>();
		v.add("java01");
		v.add("java01");
		v.add("java02");
		v.add("java03");
		v.add("java04");
		Enumeration<String> en = v.elements();
		while (en.hasMoreElements()) {
			String string = (String) en.nextElement();
			System.out.println(string);
		}
		
	}
}
