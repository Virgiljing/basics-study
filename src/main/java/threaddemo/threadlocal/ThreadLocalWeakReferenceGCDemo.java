package threaddemo.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalWeakReferenceGCDemo {
    private static final int  THREAD_LOOP_SIZE = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            ThreadLocal<Map<Integer,String>> local = new ThreadLocal<>();
            Map<Integer,String> map = new HashMap<>();
            map.put(i,"我是第" + i + "个ThreadLocal数据！");
            local.set(map);
            local.get();
            System.out.println("第" + i + "次获取ThreadLocal中的数据");

            Thread.sleep(1000);
        }
    }
}
