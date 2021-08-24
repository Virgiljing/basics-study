package threaddemo;

public class TraditionalThreadSynchronized {

	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
		
	}
	
	private void init() {
		final OutPuter putPuter = new OutPuter();
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					putPuter.output("zhangxiaoxiang");
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					putPuter.output("Jemas");
				}
			}
		}).start();
	}
	
	class OutPuter{
		public void output(String name) {
			int len = name.length();
			for (int i = 0; i < len; i++) {
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}

}
