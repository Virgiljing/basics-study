package mycollection.demo;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo {
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		al.add("java01");
		al.add("java02");
		al.add("java03");
		al.add("java04");
		
//		Iterator<String> iterator2 = al.iterator();
//		while (iterator2.hasNext()) {
//			String string = (String) iterator2.next();
//			System.out.println(string);
//		}
		
		for (Iterator<String> iterator = al.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}
}
