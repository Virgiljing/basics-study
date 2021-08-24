package pattern.proxy.staticproxy;

import java.lang.reflect.Proxy;

public class Client1 {
	public static void main(String[] args) {
		IKindWomen women = new YangPoxi();
		MyInvocationHandler handler = new MyInvocationHandler(women);
		ClassLoader loader = Client1.class.getClassLoader();
		Class<?>[] interfaces = women.getClass().getInterfaces();
		IKindWomen womens = (IKindWomen)Proxy.newProxyInstance(loader, interfaces, handler);
		womens.happyWithMan();
		womens.collect(700);
		
	}
}
