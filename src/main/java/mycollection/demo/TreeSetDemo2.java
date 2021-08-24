package mycollection.demo;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

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
 *         可以对Set集合中的元素进行排序。return 0.
 *         
 *         底层是二叉树
 *         保证元素的唯一性是compareTo方法
 *         
 *         TreeSet 排序的第一种方式：让元素自身具有比较性。
 *         元素需要实现Comparable接口，覆盖compareTo方法
 *         这种方式是元素的自然排序也叫默认顺序
 *         
 *         TreeSet 排序的第二种方式：
 *         当元素自身不具备比较性时，或者具有比较性，不是所需要的
 *         这是就需要让集合自身具有比较性。
 *         在集合初始化时，就有了比较方法。
 *  Set集合的功能和Collection是一直的。
 */
public class TreeSetDemo2 {
	public static void main(String[] args) {
		TreeSet<String> tree = new TreeSet<>();
		tree.add("aaa");
		tree.add("abc");
		tree.add("cba");
		tree.add("bbc");
		tree.add("Acb");
		
		for (Iterator<String> iterator = tree.iterator(); iterator.hasNext();) {
			String string =  iterator.next();
			System.out.println(string);
		}
		
	}
	/**
	 * 需求：往TreeSet中存放自定义对象员工
	 * 按照员工年龄排序
	 * 如果主要条件相同判断次要条件
	 */
	@Test
	public void test() {
		TreeSet<Employee> ts = new TreeSet<>();
		ts.add(new Employee("zhangsan", 22));
		ts.add(new Employee("lisi", 16));
		ts.add(new Employee("wangwu", 56));
		ts.add(new Employee("zhaoliu", 34));
		for (Iterator<Employee> iterator = ts.iterator(); iterator.hasNext();) {
			Employee employee =  iterator.next();
			System.out.println(employee);
			
		}
	}
	/**
	 * 当元素自身不具备比较性时，或者具有比较性，不是所需要的
     *    这是就需要让集合自身具有比较性。
     *    在集合初始化时，就有了比较方法。
     *    定义比较器，将比较器对象作为参数传递给TreeSet集合的构造方法中
	 */
	@Test
	public void test2() {
		TreeSet<Employee> ts = new TreeSet<>(new MyCompare());
		ts.add(new Employee("zhangsan", 22));
		ts.add(new Employee("lisi", 16));
		ts.add(new Employee("wangwu", 56));
		ts.add(new Employee("wangwu", 36));
		ts.add(new Employee("zhaoliu", 34));
		for (Iterator<Employee> iterator = ts.iterator(); iterator.hasNext();) {
			Employee employee =  iterator.next();
			System.out.println(employee);
			
		}
	}
	
	/**
	 * 练习按字符串的长度排序
	 * 字符串自身具有比较性
	 * 这是只能用比较器
	 */
	@Test
	public void test3() {
		TreeSet<String> ts = new TreeSet<>(new StrLenComparator());
		ts.add("aa");
		ts.add("bb");
		ts.add("aad");
		ts.add("saa");
		ts.add("aasdf");
		for (String string : ts) {
			System.out.println(string);
		}
	}
	
}

class StrLenComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		int num = new Integer(o1.length()).compareTo(new Integer(o2.length()));
		if (num == 0) {
			num = o1.compareTo(o2);	
		}
		return num;
	}
	
}


class MyCompare implements Comparator<Object>{

	@Override
	public int compare(Object o1, Object o2) {
		Employee em1 = (Employee)o1;
		Employee em2 = (Employee)o2;
		
		int num =  em1.getName().compareTo(em2.getName());
		if (num==0) {
			num = em1.getAge()-em2.getAge();
		}
		return num;
	}
	
}


class MyComparable implements Comparable<Object>{

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

class Employee implements Comparable<Object>{//该接口强制让员工具有比较性
	private String name;
	private int age;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String name, int age) {
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
	public int compareTo(Object o) {
		if (!(o instanceof Employee)) {
			throw new RuntimeException("不是员工对象");
		}
		Employee em = (Employee)o;
		if (this.age>em.age) {
			return 1;
		}
		if (this.age==em.age) {
			return this.name.compareTo(em.name);
		}
		return -1;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append("]");
		return builder.toString();
	}
	
	
}
