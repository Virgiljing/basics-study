package pattern2.observer2;

/**
 * @author 张航铭
 *	观察者模式
 *   -聊天室程序，服务器转发给所有客户端
 *   -网络游戏，服务器将客户端的状态进行分发
 *   -邮件订阅
 *   -Servlet中，监听器的实现
 *   -Android中，的广播机制
 *   -JDK的AWT中时间处理模型
 *   -京东商城
 */
public class Client {
	public static void main(String[] args) {
		ConcreteSubject subject = new ConcreteSubject();
		ObserverA obs1 = new ObserverA();
		ObserverA obs2 = new ObserverA();
		ObserverA obs3 = new ObserverA();
		
		subject.addObserver(obs1);
		subject.addObserver(obs2);
		subject.addObserver(obs3);
		
		subject.set(3000);
		System.out.println(obs1.getMyState());
		System.out.println(obs2.getMyState());
		System.out.println(obs3.getMyState());
		
		subject.set(900);
		System.out.println(obs1.getMyState());
		System.out.println(obs2.getMyState());
		System.out.println(obs3.getMyState());
	}
}
