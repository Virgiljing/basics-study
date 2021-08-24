package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author 张航铭
 * 
 * ? 通配符。也可以理解为占位符。生命在类型方法上不能声明在类上
 * 表示类型不定使用时确定
 * 泛型限定：
 * ? extends E:可以接受E类型或者E的子类型：上线
 * ? super E  :可以接受E类型或者E的父类型：下线
 *
 */
public class GenericDemo2 {
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		al.add("abc1");
		al.add("abc2");
		al.add("abc3");
		al.add("abc4");
		ArrayList<Integer> al2 = new ArrayList<>();
		al2.add(11);
		al2.add(22);
		al2.add(33);
		al2.add(44);
		printColl(al2);
		printColl(al);
	}
	public static void printColl(ArrayList<?> al) {
		Iterator<?> iterator = al.iterator();
		while (iterator.hasNext()) {
			Object next = iterator.next();
			System.out.println(next.toString());
		}
	}
	public static <T> void printColl1(ArrayList<T> al) {
		Iterator<T> iterator = al.iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			System.out.println(next.toString());
		}
	}
	
	public static  void printColl2(ArrayList<Person> al) {
		Iterator<Person> iterator = al.iterator();
		while (iterator.hasNext()) {
			Person person = iterator.next();
			System.out.println(person.toString());
		}
	}
	public static  void printColl3(ArrayList<? extends Person> al) {
		Iterator<? extends Person> iterator = al.iterator();
		while (iterator.hasNext()) {
			Person person = iterator.next();
			System.out.println(person.getName());
		}
	}
	@Test
	public void test() {
		Person p1 = new Person("abc1",18);
		Person p2 = new Person("abc2",28);
		Person p3 = new Person("abc3",8);
		Person p4 = new Person("abc4",16);
		Student s1 = new Student("ABC1",8);
		Student s2 = new Student("ABC2",55);
		Student s3 = new Student("ABC3",77);
		Student s4 = new Student("ABC4",38);
		ArrayList<Person> ps = new ArrayList<>();
		ps.add(p1);
		ps.add(p2);
		ps.add(p3);
		ps.add(p4);
		ArrayList<Student> ss = new ArrayList<>();
		ss.add(s1);
		ss.add(s2);
		ss.add(s3);
		ss.add(s4);
		printColl2(ps);
		//printColl2(ss);
		
		System.out.println("----------------------");
		printColl3(ps);
		printColl3(ss);
	}
	@Test
	public void test2() {
		Work w1 = new Work("w-----abc1",18);
		Work w2 = new Work("w-----abc2",28);
		Work w3 = new Work("w-----abc3",8);
		Work w4 = new Work("w-----abc4",16);
		Student s1 = new Student("ABC1",8);
		Student s2 = new Student("ABC2",55);
		Student s3 = new Student("ABC3",77);
		Student s4 = new Student("ABC4",38);
		TreeSet<Student> s = new TreeSet<>(new Comp());
		s.add(s1);
		s.add(s2);
		s.add(s3);
		s.add(s4);
		TreeSet<Work> w = new TreeSet<>(new Comp());
		w.add(w1);
		w.add(w2);
		w.add(w3);
		w.add(w4);
		for (Student student : s) {
			System.out.println(student);
		}
		for (Work work : w) {
			System.out.println(work);
		}
		
	}
}

class Person{
	private String name;
	private Integer age;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name, Integer age) {
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
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

class Student extends Person{

	public Student(String name, int age) {
		super(name, age);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [getName()=");
		builder.append(getName());
		builder.append(", getAge()=");
		builder.append(getAge());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}
	
	
}

class Work extends Person{
	public Work(String name, int age) {
		super(name, age);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Work [getName()=");
		builder.append(getName());
		builder.append(", getAge()=");
		builder.append(getAge());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append(", getClass()=");
		builder.append(getClass());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}
	
}

class Comp implements Comparator<Person>{

	@Override
	public int compare(Person p1, Person p2) {
		
		return p1.getName().compareTo(p2.getName());
	}
	
}
