package warmup;

import java.util.Arrays;
import java.util.LinkedList;


import org.junit.Test;

public class LinkedListTest {
	@Test
	public void testQuery() {
		LinkedList<Integer> list = new LinkedList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		System.out.println(list.get(3));
		System.out.println(list.get(8));
	
	}
}
