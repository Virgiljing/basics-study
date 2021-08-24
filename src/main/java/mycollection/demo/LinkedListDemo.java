package mycollection.demo;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author 张航铭
 * LinkedList:特有方法：
 * addFirst();
 * addLast();
 * 
 * 获取元素
 * 如果集合中没有元素就会出现NoSuchElementException 
 * getFirst();
 * getLast();
 * 
 * 获取元素并删除
 * 如果集合中没有元素就会出现NoSuchElementException 
 * removeFirst();
 * removeLast();
 * 
 * 在JDK1.6出现替代方法
 * 
 * offerFirst();
 * offerLast();
 * 
 * 获取元素但不删除元素。如果集合中没有元素会返回null
 * peekFirst();
 * peekLast();
 * 
 * 
 * 获取元素并删除元素。如果集合中没有元素会返回null
 * pollFirst();
 * pollLast();
 * 
 */
public class LinkedListDemo {
	public static void main(String[] args) {
		LinkedList<Object> link = new LinkedList<>();
		link.addFirst("java01");
		link.addFirst("java02");
		link.addFirst("java03");
		link.addFirst("java04");
		System.out.println(link);
		System.out.println(link.getFirst());
		System.out.println(link.size());
		System.out.println(link.removeLast());
		System.out.println(link.size());
		
		while (!link.isEmpty()) {
			System.out.println(link.removeFirst());
		}
	}
	/**
	 * 使用LinkedList 模拟一个堆栈或者队列数据结构。
	 * 
	 * 堆栈：先进后出
	 * 队列：先进先出
	 */
	@Test
	public void test() {
		
	}
}
