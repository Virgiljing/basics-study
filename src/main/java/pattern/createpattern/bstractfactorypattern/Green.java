package pattern.createpattern.bstractfactorypattern;

/**
 * Red
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
