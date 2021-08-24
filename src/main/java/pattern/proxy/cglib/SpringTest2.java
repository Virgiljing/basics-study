package pattern.proxy.cglib;

public class SpringTest2 {
	public static void main(String[] args) {
		
		LinkManDao manDao = new LinkManDao();
		LinkManDao proxy = (LinkManDao) new CglibProxy(manDao).createProxy();
		proxy.save();
		proxy.delete();
		proxy.update();
		proxy.find();
	}
}
