package pattern.createpattern.factorypattern;

/**
 * Rectangle
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
