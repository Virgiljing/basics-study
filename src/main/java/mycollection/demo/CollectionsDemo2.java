package mycollection.demo;

import java.util.*;


/**
 * 
 * Collections:
 */
public class CollectionsDemo2 {
	public static void main(String[] args) {
		//fillDemo();
		//replaceAll();
		//orderDemo();
		shuffleDemo();
	}
	
	public static void orderDemo() {
		//TreeSet<String> ts = new TreeSet<>();
		//Collections.reverseOrder()将比较器反转
		
		//TreeSet<String> ts = new TreeSet<>(Collections.reverseOrder());
		TreeSet<String> ts = new TreeSet<>(Collections.reverseOrder(new StrLenComParator2()));
		ts.add("aaaa");
		ts.add("aaab");
		ts.add("asdf");
		ts.add("asdf");
		ts.add("gf");
		ts.add("hg");
		System.out.println(ts);
	}
	 static void fillDemo() {
		List<String> list = new ArrayList<>();
		list.add("ss");
		list.add("gasdg");
		list.add("sdf");
		list.add("aadsfaa");
		list.add("sdaa");
		list.add("bcsfd");
		System.out.println(list);
		Collections.fill(list, "pp");
		System.out.println(list);
		
	}
	 static void shuffleDemo() {
		 List<String> list = new ArrayList<>();
		 list.add("ss");
		 list.add("gasdg");
		 list.add("sdf");
		 list.add("aadsfaa");
		 list.add("sdaa");
		 list.add("bcsfd");
		 System.out.println(list);
		 Collections.shuffle(list);
		 System.out.println(list);
		 
	 }
	 public static void replaceAll() {
		 List<String> list = new ArrayList<>();
			list.add("ss");
			list.add("gasdg");
			list.add("sdf");
			list.add("aadsfaa");
			list.add("sdaa");
			list.add("bcsfd");
			System.out.println(list);
			//Collections.swap(list, 2, 3);//置换位置
			Collections.replaceAll(list, "ss", "pppppp");
			System.out.println(list);
	 }
	
}


class StrLenComParator2 implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		int num = o2.length()-o1.length();
		if (num == 0) {
			num = o1.compareTo(o2);
		}
		return num;
	}
	
}