package mycollection.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @author 张航铭
 * Arrays:用于操作数组的工具类
 * asList:将数组变成List集合
 */
public class ArraysDemo {
	
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		System.out.println(Arrays.toString(arr));
		String[] arr2 = {"aba","cc","gbb"};
		List<String> list = Arrays.asList(arr2);
		//将数组变成集合不可以使用增删方法
		//以为数组的长度时固定的
		//如果增删会发生UnsupportedOperationException
		//list.add("ss");
		System.out.println("contains:"+list.contains("cc"));
		System.out.println(list);
		int[] nums = {1,2,3};
		Integer[] nums2 = {1,2,3};
		/*
		 * 如果数组中的元素都是对象那么变成集合，数组中的元素变成直接集合中的元素
		 * 如果数组中的元素都是基本数据类型，那么将该数组作为集合中的元素存在。
		 */
		List<int[]> list2 = Arrays.asList(nums);
		List<Integer> list3 = Arrays.asList(nums2);
		System.out.println(list2);
		System.out.println(list3);
	}
}
