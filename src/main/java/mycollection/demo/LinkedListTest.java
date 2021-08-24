package mycollection.demo;

import java.util.LinkedList;

/**
 * 使用LinkedList 模拟一个堆栈或者队列数据结构。
 * 
 * 堆栈：先进后出
 * 队列：先进先出
 */
public class LinkedListTest {
	public static void main(String[] args) {
		myQueue<String> queue = new myQueue<String>();
		queue.myAdd("java01");
		queue.myAdd("java02");
		queue.myAdd("java03");
		queue.myAdd("java04");
		while (!queue.isNull()) {
			System.out.println(queue.myGet());
		}
	}
}

class myQueue<E>{
	private LinkedList<E> link;
	myQueue(){
		link = new LinkedList<>();
	}
	public void myAdd(E e) {
		link.addFirst(e);
	}
	public E myGet() {
		return link.removeLast();
	}
	public boolean isNull() {
		return link.isEmpty();
	}
}