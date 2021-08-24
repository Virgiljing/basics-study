package pattern.prototype;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sheep2 implements Cloneable {
	private String sname;
	private Date birthday;
	private List<String> hobbys;
	
	public Sheep2(String sname, Date birthday, List<String> hobbys) {
		super();
		this.sname = sname;
		this.birthday = birthday;
		this.hobbys = hobbys;
	}
	public List<String> getHobbys() {
		return hobbys;
	}
	public void setHobbys(List<String> hobbys) {
		this.hobbys = hobbys;
	}
	@Override
	protected Sheep2 clone() throws CloneNotSupportedException {
		Object obj = super.clone();
		Sheep2 sheep2 = (Sheep2)obj;
		//创建新集合使Sheep2实现深拷贝
		List<String> hobbys = new ArrayList<>();
		for(String hobby:this.getHobbys()) {
			hobbys.add(hobby);
		}
		sheep2.setHobbys(hobbys);
		return sheep2;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Sheep2() {
		super();
	}
	public Sheep2(String sname, Date birthday) {
		super();
		this.sname = sname;
		this.birthday = birthday;
	}
	
}
