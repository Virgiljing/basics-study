package mycollection.demo;

import java.util.ArrayList;
import java.util.Arrays;

public class CollectionsToArray {
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		al.add("abc1");
		al.add("abc2");
		al.add("abc3");
		al.add("abc4");
		/*
		 * 指定类型的数组长度小于集合的size，那么该方法内部会创建一个新的数组，长度为集合的size
		 * 当指定类型数组的长的大于集合的size，就不会创建新数组，而是使用传递进来的数组
		 * 
		 * 集合便数组为了限定对元素的操作
		 */
		String[] arr = al.toArray(new String[5]);
		System.out.println(Arrays.asList(arr));
		
	}
}
