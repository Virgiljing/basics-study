package pattern2.memento;

public class Client {

	public static void main(String[] args) {
		CareTaker taker = new CareTaker();
		Emp emp = new Emp("张三", 29, 90000);
		System.out.println("第一次创建对象："+emp.getEname()+"-----"+emp.getAge()+"-----"+emp.getSalary());
		taker.setMemento(emp.memento());//备忘一次
		emp.setAge(88);
		emp.setEname("李四");
		emp.setSalary(8000);
		System.out.println("第二次创建对象："+emp.getEname()+"-----"+emp.getAge()+"-----"+emp.getSalary());
		emp.recovery(taker.getMemento());//恢复到备忘录对象保存的状态
		System.out.println("第三次创建对象："+emp.getEname()+"-----"+emp.getAge()+"-----"+emp.getSalary());
	}

}
