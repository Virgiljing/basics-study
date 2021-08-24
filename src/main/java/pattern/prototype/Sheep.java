package pattern.prototype;

import java.util.Date;
import java.util.List;

public class Sheep implements Cloneable {
	private String sname;
	private Date birthday;
	private List<String> hobbys;
	
	public Sheep(String sname, Date birthday, List<String> hobbys) {
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
	protected Object clone() throws CloneNotSupportedException {
		Object obj = super.clone();
		
		return obj;
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
	public Sheep() {
		super();
	}
	public Sheep(String sname, Date birthday) {
		super();
		this.sname = sname;
		this.birthday = birthday;
	}
	
}
