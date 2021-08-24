package mycollection.demo;

import java.util.HashMap;

public class MapTest {
	public static void main(String[] args) {
		HashMap<Student2, String> hm = new HashMap<>();
		hm.put(new Student2("lisi1", 22), "beijing");
		hm.put(new Student2("lisi1", 22), "tianjin");
		hm.put(new Student2("lisi2", 23), "shanghai");
		hm.put(new Student2("lisi3", 12), "shenzhen");
		hm.put(new Student2("lisi4", 55), "guangzhou");
		
		for(Student2 stu: hm.keySet()) {
			System.out.println(stu);
		}
		for(java.util.Map.Entry<Student2, String> entry:hm.entrySet()) {
			System.out.println(entry.getKey()+"::::"+entry.getValue());
		}
	}
}

class Student2 implements Comparable<Student2>{
	private String name;
	private int age;
	
	public Student2() {
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
		Student2 other = (Student2) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Student2(String name, int age) {
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
	public int compareTo(Student2 o) {
		int num = new Integer(this.age).compareTo(new Integer(o.age));
		if (num==0) {
			num = this.name.compareTo(o.name);
		}
		return num;
	}
	
}