package pattern.createpattern.singleton;

/**
 * Singleton4
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class Singleton4 {
    private static class SingletonHolder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }
    private Singleton4 (){}
    public static final Singleton4 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
