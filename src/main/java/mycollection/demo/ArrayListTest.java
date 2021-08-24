package mycollection.demo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 张航铭
 *	去除ArrayList集合中的重复元素
 *	在迭代时循环中next值调用一次hasNext只判断一次
 */
public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		al.add("java01");
		al.add("java02");
		al.add("java03");
		al.add("java01");
		al.add("java02");
		al.add("java04");
		al.add("java03");
		System.out.println(al);
		ArrayList<String> list = singleElement(al);
		System.out.println(list);
	}
	public static <E> ArrayList<E> singleElement(ArrayList<E> ar) {
		ArrayList<E> newAl = new ArrayList<>();
		Iterator<E> iterator = ar.iterator();
		while (iterator.hasNext()) {
			E e = (E) iterator.next();
			if (!newAl.contains(e)) {
				newAl.add(e);
			}
		}
		return newAl;
	}
}
