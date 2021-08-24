package pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler{
	private Object obj;
	public JdkProxy(Object obj) {
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if ("save".equals(method.getName())||"update".equals(method.getName())) {
			checkPrivilege();
		}
		Object value = method.invoke(obj, args);
		if ("delete".equals(method.getName())) {
			writeLog();
		}
		return value;
	}
	
	private void checkPrivilege() {
		System.out.println("权限校验------");
	}
	private void writeLog() {
		System.out.println("打印日志--------");
	}
	public Object createProxy() {
		Object proxy = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
		return proxy;
	}

}
