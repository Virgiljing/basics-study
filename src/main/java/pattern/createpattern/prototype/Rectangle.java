package pattern.createpattern.prototype;

/**
 * Rectangle
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class Rectangle extends Shape {
    @Override
    void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }

    public Rectangle(){
        type = "Rectangle";
    }
}
