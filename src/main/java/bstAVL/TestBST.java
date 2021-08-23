package bstAVL;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

public class TestBST {
	private Random random = new Random();
	private final int MAX1 = 16;
	@Test
	public void testPutAndItr() {
		AVLMap<Integer, String> map = new AVLMap<>();
		for(int i=0;i<MAX1;i++) {
			map.put(random.nextInt(MAX1), random.nextInt(MAX1)+"");
		}
		Iterator<AVLEntry<Integer, String>> iterator = map.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next().key+" ");
		}
		System.out.println();
	}
	private final int MAX2 = 655;
	@Test
	public void testPutAndItrWithJDK() {
		AVLMap<Integer, String> map1 = new AVLMap<>();
		TreeMap<Integer, String> map2 = new TreeMap<>();
		for(int i=0;i<MAX2;i++) {
			map1.put(random.nextInt(MAX2), random.nextInt(MAX1)+"");
			map2.put(random.nextInt(MAX2), random.nextInt(MAX1)+"");
		}
		//Assert.assertTrue(map1.size()==map2.size());
		System.out.println(map1.size());
		System.out.println(map2.size());
		System.out.println("----------------------------");
		Iterator<AVLEntry<Integer, String>> iterator = map1.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next().value+":");
		}
		System.out.println("--------------------");
		Set<Integer> keySet = map2.keySet();
		for (Integer key : keySet) {
			String value = map2.get(key);
			System.out.print(value+":");
		}
		System.out.println("---------------------");
	}
}
