package threaddemo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
	public static void main(String[] args) {
//		new Timer().schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				System.out.println("bombing");
//			}
//		}, 3000,3000);
		
		
		new Timer().schedule(new MyTimerTask(), 2000);
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}

class MyTimerTask extends TimerTask{
	static int count = 0;
	@Override
	public void run() {
		count = (count+1)%2;
		System.out.println("bombing...");
		new Timer().schedule(new MyTimerTask(), 2000+2000*count);
	}
	
}