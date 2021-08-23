package pattern.createpattern.singleton;

/**
 * Singleton2
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class Singleton2 {

    private static Singleton2 instance = new Singleton2();
    private Singleton2 (){}
    public static Singleton2 getInstance() {
        return instance;
    }

}
