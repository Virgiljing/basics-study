package pattern.adapter;

/**
 * @author 张航铭
 * 客户端类
 * 适配器模式
 */
public class Client {


	public void test1(Target t) {
		t.handleReq();
	}

	public static void main(String[] args) {

		Client c = new Client();
		Adaptee adaptee = new Adaptee();
		Target t = new Adpater();
		c.test1(t);


		Adpater adpater = new Adpater();
		adpater.handleReq();
		Target t1 = new Adpater2(adaptee);
		t1.handleReq();
 	}
}
