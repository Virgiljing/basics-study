package pattern2.observer;

public class Client {
	public static void main(String[] args) {
		 ConcreteSubject subject = new ConcreteSubject();
		 ObserverA obs1 = new  ObserverA();
		 ObserverA obs2 = new  ObserverA();
		 ObserverA obs3 = new  ObserverA();
		 
		 subject.registerObserver(obs1);
		 subject.registerObserver(obs2);
		 subject.registerObserver(obs3);
		 
		 subject.setState(3000);
		 
		 System.out.println(obs1.getMyState());
		 System.out.println(obs2.getMyState());
		 System.out.println(obs3.getMyState());
		 subject.setState(600);
		 
		 System.out.println(obs1.getMyState());
		 System.out.println(obs2.getMyState());
		 System.out.println(obs3.getMyState());
	}
}
