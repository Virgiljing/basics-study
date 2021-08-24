package threaddemo;

public class ThreadTest {
	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("1:"+Thread.currentThread());
					System.out.println("2:"+this.getName());
				}
			}
		};
		
		thread.start();
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("1:"+Thread.currentThread());
				}
			}
		});
		thread2.start();
		
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("thread :"+Thread.currentThread());
				}
			}
		}.start();
		
	}
}
