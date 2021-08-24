package pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
	public static void main(String[] args) {
		ClassLoader loader = Client.class.getClassLoader();
		final IKindWomen women = new JinLian();
		Class<? extends IKindWomen> clazz = women.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
		
		IKindWomen proxy = (IKindWomen) Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
			private void openHouse() {
				System.out.println("将两人带到开好的房间");
			}
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				String name = method.getName();
				if (name.equals("happyWithMan")) {
					openHouse();
					method.invoke(women, args);
					clear();
					return null;
				}else if (name.equals("collect")) {
					double money = (double)args[0];
					System.out.println("平台收取中介费"+money*0.3);
					method.invoke(women, money*0.7);
					return money*0.3;
				}else {
					
					return method.invoke(women, args);
				}
				
			}
			private void clear() {
				System.out.println("打扫战场。");
			}
		});
		proxy.happyWithMan();
		double collect = proxy.collect(500);
		System.out.println(collect);
		System.out.println(proxy.toString());
		
	}
}
