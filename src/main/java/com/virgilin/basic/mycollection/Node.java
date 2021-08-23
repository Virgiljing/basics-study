package com.virgilin.basic.mycollection;

public class Node<T> {
	private Node<T> previous;
	private T t;
	private Node<T> next;
	public Node() {}
	public Node(Node<T> previous,T t,Node<T> next) {
		this.previous = previous;
		this.t = t;
		this.next = next;
	}
	public Node<T> getPrevious() {
		return previous;
	}
	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}
