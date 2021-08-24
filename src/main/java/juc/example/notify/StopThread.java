package juc.example.notify;

/**
 * StopThread
 *
 * @author virgilin
 * @date 2019/4/15
 */
class StopThreadDemo extends Thread{

    private volatile boolean flag = true;
    @Override
    public void run() {
        System.out.println("子线程开始");
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                setFlag();
            }
        }
        System.out.println("子线程结束");
    }

    public void setFlag() {
        System.out.println("调用stopThread方法");
        this.flag = false;
        System.out.println("已经将flag修改为false");
    }
}
public class StopThread {
    public static void main(String[] args) {
        StopThreadDemo stopThreadDemo = new StopThreadDemo();
        stopThreadDemo.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("我是主线程i:" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if (i == 6) {
//                stopThreadDemo.setFlag();
//            }
            if (i == 3) {
                stopThreadDemo.interrupt();
            }
        }
    }
}
