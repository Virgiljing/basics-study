package jvm;

import java.io.Serializable;

/**
 * TestJvm
 *
 * @author virgilin
 * @date 2019/3/4
 */
public class TestJvm  implements Serializable,Runnable {
//    static String name = "virgilin";
    static int a ;
    public static byte b;
    public long c;
    @Override
    public void run() {
        System.out.println("run");
    }

    public static void main(String[] args) {
        TestJvm testJvm = new TestJvm();
        new Thread(testJvm).start();
    }

    public static void setA(int a) {
        TestJvm.a = a;
    }
}
