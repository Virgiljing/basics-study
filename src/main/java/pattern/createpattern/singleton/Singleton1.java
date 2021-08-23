package pattern.createpattern.singleton;

/**
 * Singleton1
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class Singleton1 {
    private static Singleton1 instance;
    private Singleton1 (){}
    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
