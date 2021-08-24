package mycollection.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @author 张航铭
 * 集合框架工具类
 * Collections:
 */
public class CollectionsDemo {
	public static void main(String[] args) {
		sortDemo();
		maxDemo();
		binarySearchDemo();
	}
	public static int halfSearch(List<String> list,String key,Comparator<String> com) {
		int max,min,mid,num;
		max = list.size() - 1;
		min = 0;
		while (min<=max) {
			mid = (max+min)>>1;
			String str = list.get(mid);
			num = com.compare(str, key);
			if (num>0) {
				max = mid - 1;
			}else if (num<0) {
				min = mid + 1;
			}else {
				return mid;
			}
		}
		return -min-1;
	}
	public static int halfSearch(List<String> list,String key) {
		int max,min,mid,num;
		max = list.size() - 1;
		min = 0;
		while (min<=max) {
			mid = (max+min)>>1;
			String str = list.get(mid);
			num = str.compareTo(key);
			if (num>0) {
				max = mid - 1;
			}else if (num<0) {
				min = mid + 1;
			}else {
				return mid;
			}
		}
		return -min-1;
	} 
	
	public static void binarySearchDemo() {
		List<String> list = new ArrayList<>();
		list.add("ss");
		list.add("gasdg");
		list.add("sdf");
		list.add("aadsfaa");
		list.add("sdaa");
		list.add("bcsfd");
		Collections.sort(list);
		System.out.println(list);
		//int index = Collections.binarySearch(list,"ss");
		int index = halfSearch(list, "sdff",new StrLenComParator1());
		System.out.println(index);
	}
	public static void maxDemo() {
		List<String> list = new ArrayList<>();
		list.add("ss");
		list.add("gasdg");
		list.add("sdf");
		list.add("aadsfaa");
		list.add("sdaa");
		list.add("bcsfd");
		String max = Collections.max(list,new StrLenComParator1());
		System.out.println(max);
	}
	public static void sortDemo() {
		List<String> list = new ArrayList<>();
		list.add("ss");
		list.add("gasdg");
		list.add("sdf");
		list.add("aadsfaa");
		list.add("sdaa");
		list.add("bcsfd");
		System.out.println(list);
		Collections.sort(list, new StrLenComParator1());
		System.out.println(list);
	}
}

class StrLenComParator1 implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		int num = o2.length()-o1.length();
		if (num == 0) {
			num = o1.compareTo(o2);
		}
		return num;
	}
	
}
