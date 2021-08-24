package pattern.prototype;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 张航铭
 *	测试原型模式
 */
public class Client3 {
	public static void main(String[] args) throws CloneNotSupportedException {
		List<String> hobbys = new ArrayList<>();
		hobbys.add("篮球");
		Sheep3 sheep = new Sheep3("多利", new Date(123213213213L),hobbys) ;
		System.out.println(sheep);
		System.out.println(sheep.getSname());
		System.out.println(sheep.getBirthday());
		System.out.println(sheep.getHobbys().size());
		System.out.println("-----------------------------------");
		
		Sheep3 sheep2 = (Sheep3) sheep.clone();
		//日期对象实现的是深拷贝
		sheep.setBirthday(new Date(439812343432L));
		System.out.println("修改后的日期对象"+sheep.getBirthday());
		//数组对象实现的是浅拷贝
		sheep.getHobbys().add("足球");
		
		sheep2.setSname("少利");
		System.out.println(sheep2);
		
		System.out.println(sheep2.getSname());
		System.out.println(sheep2.getBirthday());
		System.out.println(sheep2.getHobbys().size());
		
	}
}
