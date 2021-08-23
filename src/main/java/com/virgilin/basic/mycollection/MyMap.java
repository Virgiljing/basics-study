package com.virgilin.basic.mycollection;

public class MyMap<K,V> {
	class myEntry<X,Y>{
		X key;
		Y value;
		public myEntry(X key, Y value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	private myEntry<K,V>[] arr = new myEntry[100];
	private int size;
	public void put(K key,V value) {
		for (int i = 0; i < size; i++) {
			if (arr[i].equals(key)) {
				arr[i].value = value;
				return;
			}
		}
		myEntry<K, V> e = new myEntry<K, V>(key, value);
		arr[size++] = e;
	}
	public V get(K key) {
		for (int i = 0; i < size; i++) {
			if (arr[i].equals(key)) {
				return arr[i].value;
			}
		}
		return null;
	}
}
