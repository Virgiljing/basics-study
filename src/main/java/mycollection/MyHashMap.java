package mycollection;

public class MyHashMap<K,V> {
	private MyLinkedList<myEntry<K,V>>[] arr = new MyLinkedList[999];
	private int size;
	class myEntry<X,Y>{
		X key;
		Y value;
		public myEntry(X key, Y value) {
			super();
			this.key = key;
			this.value = value;
		}
	}
	public int size() {
		return size;
	}
	public void put(K key,V value) {
		myEntry<K,V> e = new myEntry<>(key, value);
		int a = key.hashCode()%999;
		if (arr[a]==null) {
			MyLinkedList<myEntry<K,V>> list = new MyLinkedList<>();
			arr[a] = list;
			arr[a].add(e);
		}else {
			MyLinkedList<myEntry<K,V>> list = arr[a];
			for (int i = 0; i < list.size(); i++) {
				myEntry<K, V> e2 = list.get(i);
				if (e2.key.equals(key)) {
					e2.value = value;
					return ;
				}
			}
			arr[a].add(e);
		}
		size ++;
	}
	public V get(K key) {
		int a = key.hashCode()%999;
		if (arr[a]!=null) {
			MyLinkedList<myEntry<K,V>> list = arr[a];
			for (int i = 0; i < list.size(); i++) {
				myEntry<K, V> e = list.get(i);
				if (e.key.equals(key)) {
					return e.value;
				}
			}
		}
		return null;
	}
	public static void main(String[] args) {
		MyHashMap<String, String> map = new MyHashMap<>();
		map.put("zhang", "san");
		map.put("li", "si");
		map.put("zhang", "wang");
		System.out.println(map.get("zhang"));
	}
}
