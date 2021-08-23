package pattern.createpattern.prototype;

/**
 * Square
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class Square extends Shape {
    @Override
    void draw() {
        System.out.println("Inside Square::draw() method.");
    }

    public Square(){
        type = "Square";
    }
}
