package mycollection.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * @author 张航铭
 * 
 * Collection
 *     |--List: 元素是有序的，元素可以重复。因为该集合体系有索引。
 *         |--ArrayList:低层的数据结构使用的是数组结构。特点：查询速度快，增删稍慢。线程不同步
 *         |--LinkedList:底层使用的链表数据结构。特点：增删速度很快，查询稍慢。
 *         |--Vector:底层使数组数据结构。线程同步。被ArrayList替代
 *     |--Set : 元素是有序，元素不可以重复
 *     
 *     
 *     
 *  List集合中特有的迭代器。ListIterator是Iterator的子接口。
 *  在迭代时，不可以通过集合对象的方法操作集合中的元素。
 *  因为会发生ConcurrentModificationException异常。
 *  
 *  所以在迭代时，只能用迭代器的方法操作元素，但是Iterator的方法很少
 *  只能对元素进行判断，取出，删除的操作，
 *  如果想添加，修改等就需要使用其子接口，ListIterator
 *  
 *  该接口只能通过List集合的listIterator方法获取
 */
public class ListDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("java01");
		list.add("java02");
		list.add("java03");
		list.add("java04");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("-----------------------");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
		//通过indexOf获取对象的位置
		System.out.println(list.indexOf("java02"));
		List<String> subList = list.subList(1, 3);
		System.out.println(subList);
		
		//在迭代过程中，准备添加或删除元素
		System.out.println("------------------------");
		Iterator<String> iterator2 = list.iterator();
		while (iterator2.hasNext()) {
			String string = (String) iterator2.next();
			if (string.equals("java02")) {
				//list.add("java008");
				//java.util.ConcurrentModificationException
				iterator2.remove();//java02从list中移除
				System.out.println(string);
			}
		}
		System.out.println(list);
		
		
		System.out.println("-----------------------------");
		ListIterator<String> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			String string = (String) listIterator.next();
			if (string.equals("java03")) {
				//listIterator.add("java009");
				listIterator.set("java100");
			}
		}
		System.out.println(list);
		System.out.println("----------------------");
		System.out.println(listIterator.hasPrevious());//前边是否有元素
		System.out.println(listIterator.hasNext());//后边是否有元素
		while (listIterator.hasPrevious()) {
			System.out.println(listIterator.previous());
		}
	}
}
