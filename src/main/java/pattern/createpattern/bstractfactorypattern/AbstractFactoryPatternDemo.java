package pattern.createpattern.bstractfactorypattern;

/**
 * AbstractFactoryPatternDemo
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getAbstracFactory(FactoryType.SHAPE);
        Shape circle = shapeFactory.getShape(ShapeType.CIRCLE);
        circle.draw();
        Shape square = shapeFactory.getShape(ShapeType.SQUARE);
        square.draw();

        Shape rectangle = shapeFactory.getShape(ShapeType.RECTANGLE);
        rectangle.draw();

        AbstractFactory colorFactory = FactoryProducer.getAbstracFactory(FactoryType.COLOR);
        Color red = colorFactory.getColor(ColorType.RED);
        red.fill();
        Color green = colorFactory.getColor(ColorType.GREEN);
        green.fill();
        Color blue = colorFactory.getColor(ColorType.BLUE);
        blue.fill();
    }
}
