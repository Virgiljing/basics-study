package pattern.proxy.staticproxy;

import java.lang.reflect.Proxy;

public class Client3 {
	public static void main(String[] args) {
		IPerson man = new Man();
		ClassLoader loader = Client3.class.getClassLoader();
		Class<?>[] interfaces = man.getClass().getInterfaces();
		IPerson man1 = (IPerson) Proxy.newProxyInstance(loader, interfaces, new MyInvocationHandler(man));
		man1.eat();
		man1.run();
		man1.study();
	}
}
