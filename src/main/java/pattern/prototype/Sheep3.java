package pattern.prototype;

import java.io.*;
import java.util.Date;
import java.util.List;

public class Sheep3 implements Cloneable,Serializable {
	private static final long serialVersionUID = 1L;
	private String sname;
	private Date birthday;
	private List<String> hobbys;
	
	public Sheep3(String sname, Date birthday, List<String> hobbys) {
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
	protected Sheep3 clone() throws CloneNotSupportedException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		Sheep3 sheep = null;
		try {
			//使用序列化与反序列化的方法实现深拷贝
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(this);
			byte[] bs = bo.toByteArray();
			ByteArrayInputStream bi = new ByteArrayInputStream(bs);
			ObjectInputStream oi = new ObjectInputStream(bi);
			sheep = (Sheep3) oi.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sheep;
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
	public Sheep3() {
		super();
	}
	public Sheep3(String sname, Date birthday) {
		super();
		this.sname = sname;
		this.birthday = birthday;
	}
	
}
