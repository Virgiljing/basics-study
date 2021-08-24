package pattern.single;

public class Client {
	public static void main(String[] args) {
		SingletonDemo01 instance1 = SingletonDemo01.getInstance();
		SingletonDemo01 instance2 = SingletonDemo01.getInstance();
		System.out.println(instance1==instance2);
		SingletonDemo05 instance = SingletonDemo05.INSTANCE;
		SingletonDemo05 instance3 = SingletonDemo05.INSTANCE;
		System.out.println(instance);
		System.out.println(instance3);
		
		System.out.println(instance=instance3);
	}
}
