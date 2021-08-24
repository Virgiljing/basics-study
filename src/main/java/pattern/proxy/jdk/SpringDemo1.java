package pattern.proxy.jdk;

import org.junit.Test;

public class SpringDemo1 {
	@Test
	public void demo1() {
		CustomerDao customerDao = new CustomerDaoImpl();
		CustomerDao proxy = (CustomerDao) new JdkProxy(customerDao).createProxy();
//		proxy.save();
//		proxy.find();
//		proxy.delete();
		proxy.update();
	}
}
