package pattern.createpattern.bstractfactorypattern;




/**
 * Circle
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
