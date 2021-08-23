package com.virgilin.basic.mycollection;

public class Test {
	public static void main(String[] args) {
		MyLinkedList<String> ml = new MyLinkedList<>();
		ml.add("aaa");
		ml.add("bbb");
		ml.add("ccc");
		ml.remove(1);
		System.out.println(ml.size());
		System.out.println(ml.get(0));
		System.out.println(ml.get(1));
	}
}
