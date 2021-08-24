package mycollection.demo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 张航铭
 *  
 *  |Set:元素是无序(存入和取出的顺序不一定一致)，元素不可以重复。
 *  	|--HashSet:底层数据结构是Hash表
 *  	         是通过元素的两个方法，hashCode和equals来完成。
 *  	         如果元素的HashCode值相同，才会判断equals是否为true。
 *         如果元素的hashCode值不同，不会调用equals。       
 *         
 *         注意，对于判断元素是否存在，一级删除等操作，依赖的方法是元素的hashCode和equals方法。
 *  	|--TreeSet:
 *  Set集合的功能和Collection是一直的。
 */
public class HashSetTest{
	public static void main(String[] args) {
		Set<Student> set = new HashSet<>();
		set.add(new Student("lisi", 18));
		set.add(new Student("lisi", 18));
		set.add(new Student("zhangsan", 23));
		set.add(new Student("wangwu", 10));
		set.add(new Student("zhaoliu", 35));
		
		System.out.println(set.contains(new Student("zhangsan", 23)));
		
		System.out.println(set.remove(new Student("zhaoliu",35)));
		
		System.out.println("---------------------");
		for (Iterator<Student> iterator = set.iterator(); iterator.hasNext();) {
			Student student = iterator.next();
			System.out.println(student);
		}
	}
}

class Student{
	private String name;
	private int age;
	@Override
	public int hashCode() {
		System.out.println(this.name+"————————hashCode");
		return name.hashCode()+age*39;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Student)) {
			return false;
		}
		Student person = (Student)obj;
		System.out.println(this.name+"-----"+person.name);
		return this.name.equals(person.name)&&this.age==person.age;
	}
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name, int age) {
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