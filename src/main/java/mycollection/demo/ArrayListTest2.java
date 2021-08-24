package mycollection.demo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 张航铭
 *  
 *  将自定义对象作为元素存到ArrayList集合中，并去除重复元素。
 *  比如：人对象。同姓名同年龄，视为同一个人。为重复元素。
 *  
 *  List集合判断元素是否相同，依据的是元素的equals方法
 */

class Person{
	private String name;
	private int age;
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Person)) {
			return false;
		}
		Person person = (Person)obj;
		System.out.println(this.name+"-----"+person.name);
		return this.name.equals(person.name)&&this.age==person.age;
	}
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append("]");
		return builder.toString();
	}
	
	
}

public class ArrayListTest2 {
	public static void main(String[] args) {
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person("lisi01", 30));
		list.add(new Person("lisi02", 32));
		list.add(new Person("lisi03", 34));
		list.add(new Person("lisi04", 36));
		list.add(new Person("lisi04", 36));
		for (Iterator<Person> iterator = list.iterator(); iterator.hasNext();) {
			Person person =  iterator.next();
			System.out.println(person);
		}
		System.out.println("--------------------------------");
		ArrayList<Person> list2 = singleElement(list);
		System.out.println(list2);
		
		System.out.println("---------------------____________");
		System.out.println("remove 03:"+list2.remove(new Person("lisi03",34)));
		System.out.println(list2);
	}
	
	public static <E> ArrayList<E> singleElement(ArrayList<E> list){
		ArrayList<E> list2 = new ArrayList<>();
		Iterator<E> iterator = list.iterator();
		while (iterator.hasNext()) {
			E e = (E) iterator.next();
			if (!list2.contains(e)) {
				
				//ArrayList中判断元素是否相同有equals方法
				list2.add(e);
			}
		}
		return list2;
	}
}
