package pattern.createpattern.prototype;

/**
 * Circle
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class Circle extends Shape {
    public Circle(){
        type = "Circle";
    }

    @Override
    void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
