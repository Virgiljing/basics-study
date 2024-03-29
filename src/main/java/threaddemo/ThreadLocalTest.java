package threaddemo;

import java.util.Random;

public class ThreadLocalTest {
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()+" has put data:"+data);
					threadLocal.set(data);
					new A().get();
					new B().get();
				}
			}).start();
			
		}
	}
	
	static class A{
		public void get() {
			int data = threadLocal.get();
			System.out.println("A from "+Thread.currentThread().getName()+"has get data:"+data);
		}
	}
	static class B{
		public void get() {
			int data = threadLocal.get();
			System.out.println("B from "+Thread.currentThread().getName()+"has get data:"+data);
		}
	}
}
