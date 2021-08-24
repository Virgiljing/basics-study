package pattern.single;

import java.util.concurrent.CountDownLatch;

/**
 * @author 张航铭
 * 测试多线程环境下五种创建单例模式的效率
 *
 */
public class Client3 {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		int num = 10;
		final CountDownLatch countDownLatch = new CountDownLatch(num);
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100000; j++) {
						Object instance = SingletonDemo04.getInstance();
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		countDownLatch.await();//main线程阻塞 ，知道计数器变为0，才会继续往下走
		long end = System.currentTimeMillis();
		System.out.println("总耗时:"+(end-start));
	}
}
