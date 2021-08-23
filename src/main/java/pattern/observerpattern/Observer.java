package pattern.observerpattern;

/**
 * Observer
 *
 * @author virgilin
 * @date 2019/4/9
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
