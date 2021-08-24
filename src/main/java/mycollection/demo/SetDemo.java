package mycollection.demo;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @author 张航铭
 *  
 *  |Set:元素是无序(存入和取出的顺序不一定一致)，元素不可以重复。
 *  	|--HashSet:底层数据结构是Hash表
 *  	|--TreeSet:
 *  Set集合的功能和Collection是一直的。
 */
public class SetDemo {
	public static void main(String[] args) {
		HashSet<String> set = new HashSet<>();
		
		System.out.println(set.add("java01"));
		System.out.println(set.add("java01"));
		set.add("java01");
		set.add("java02");
		set.add("java03");
		set.add("java03");
		set.add("java04");
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			System.out.println(string);
			
		}
	}
}
