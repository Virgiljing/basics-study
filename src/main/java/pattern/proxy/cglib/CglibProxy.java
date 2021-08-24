package pattern.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;



public class CglibProxy implements MethodInterceptor {
	private Object obj;
	public CglibProxy(Object obj) {
		this.obj = obj;
	}
	private void writeLog() {
		System.out.println("打印日志----------");
	}
	
	private void chechPrivilege() {
		System.out.println("权限校验----------");
	}
	public Object createProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(this);
		Object object = enhancer.create();
		return object;
	}
	@Override
	public Object intercept(Object object, Method method, Object[] arg2, MethodProxy arg3) throws Throwable {
		if ("save".equals(method.getName())||"update".equals(method.getName())) {
			chechPrivilege();
		}
		Object ob = method.invoke(obj, arg2);
		if ("delete".equals(method.getName())) {
			writeLog();
		}
		return ob;
	}
}
