package mycollection;

import java.util.Iterator;

public class MyArrayList {
	private Object[] elementData;
	private int size;
	public int size() {
		return size;
	}
	public MyArrayList() {
		this(10);
	}
	public MyArrayList(int initialCapacity) {
		if (initialCapacity<0) {
			throw new RuntimeException("初始化值不能小于0");
		}
		elementData = new Object[initialCapacity];
	}
	//判断索引是否越界
	private void rangeCheck(int index) {
		if (index<0||index>=size) {
			throw new RuntimeException("索引越界异常"+index);
		}
	}
	public void add(Object date) {
		//数组扩容和数据的拷贝
		if (size+1>elementData.length) {
			Object[] newArray = new Object[size*2+1];
			System.arraycopy(elementData, 0, newArray, 0, elementData.length);
			elementData = newArray;
		}
		elementData[size++] = date;
	}
	
	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}
	public void remove(int index) {
		rangeCheck(index);
		//删除指定位置的对象
		int numMoved = size - index -1;
		if (numMoved>0) {
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size] = null;
	}
	public void remove(Object o) {
		for(int i=0;i<size;i++) {
			if (get(i).equals(o)) {
				remove(i);
				return;
			}
		}
	}
	public Object set(int index,Object o) {
		rangeCheck(index);
		Object oldValue = elementData[index];
		elementData[index] = o;
		return oldValue;
	}
	public Iter Iterator() {
		return new Iter();
	}
	private class Iter implements Iterator<Object>{
		int corsor;
		int lastRet = -1;
		@Override
		public boolean hasNext() {
			return corsor!=size;
		}

		@Override
		public Object next() {
			Object o = get(corsor);
			corsor++;
			lastRet = corsor;
			return o;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}}
	public void add(int index,Object o) {
		rangeCheck(index);
		//数组扩容和数据的拷贝
		if (size+1>elementData.length) {
			Object[] newArray = new Object[size*2+1];
			System.arraycopy(elementData, 0, newArray, 0, elementData.length);
			elementData = newArray;
		}
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	
	
	public static void main(String[] args) {
		MyArrayList ml = new MyArrayList(3);
		ml.add("lisi1");
		ml.add("lisi2");
		ml.add("lisi3");
		ml.add("lisi4");
		System.out.println(ml.size());
		System.out.println(ml.get(2));
		System.out.println(ml.get(3));
	}
}
