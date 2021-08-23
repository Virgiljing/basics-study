package pattern.createpattern.factorypattern;

/**
 * Square
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
