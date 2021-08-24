package mycollection.demo;

import java.util.Comparator;
import java.util.TreeMap;

public class MapTest2 {
	public static void main(String[] args) {
		TreeMap<Student3, String> tm = new TreeMap<>( new StuNameComparator());
		tm.put(new Student3("lisi1", 22), "beijing");
		tm.put(new Student3("lisi1", 22), "tianjin");
		tm.put(new Student3("lisi2", 23), "shanghai");
		tm.put(new Student3("lisi3", 12), "shenzhen");
		tm.put(new Student3("lisi4", 55), "guangzhou");
		
		for(Student3 stu:tm.keySet()) {
			System.out.println(stu);
		}
		for(java.util.Map.Entry<Student3, String> entry:tm.entrySet()) {
			System.out.println(entry.getKey()+"::::"+entry.getValue());
		}
	}
}

class StuNameComparator implements Comparator<Student3>{

	@Override
	public int compare(Student3 o1, Student3 o2) {
		int num = o1.getName().compareTo(o2.getName());
		if (num==0) {
			num = new Integer(o1.getAge()).compareTo(new Integer(o2.getAge()));
		}
		return num;
	}
	
}

class Student3 implements Comparable<Student3>{
	private String name;
	private int age;
	
	public Student3() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student3 other = (Student3) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Student3(String name, int age) {
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
		builder.append("Student [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Student3 o) {
		int num = new Integer(this.age).compareTo(new Integer(o.age));
		if (num==0) {
			num = this.name.compareTo(o.name);
		}
		return num;
	}
	
}