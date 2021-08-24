package juc.example.notify;

/**
 * OutInputThread
 * volatile关键字具有可见性，有序性，不具有原子性
 * @author virgilin
 * @date 2019/4/15
 */
class Res{
    public String userName;
    public String sex;
    public volatile boolean flag = false;

}

class Out extends Thread{
    Res res;

    public Out(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            synchronized (res){
                if (!res.flag){
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(res.userName + ":" + res.sex);
                res.flag = false;
                res.notify();
            }
        }
    }
}

class Input extends Thread{
    Res res;

    public Input(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true){
            synchronized (res){
                if (res.flag){
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (count == 0){
                    res.userName = "小红";
                    res.sex = "女";
                }else {
                    res.userName = "小明";
                    res.sex = "男";
                }

                count = (count +1 ) % 2;
                res.flag = true;
                res.notify();
            }
        }
    }
}
public class OutInputThread {
    public static void main(String[] args) {
        Res res = new Res();
        Out out = new Out(res);
        Input input = new Input(res);
        out.start();
        input.start();

    }
}
