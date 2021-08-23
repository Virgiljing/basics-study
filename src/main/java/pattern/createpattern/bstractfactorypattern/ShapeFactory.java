package pattern.createpattern.bstractfactorypattern;

/**
 * ShapeFactory
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(ColorType colorType) {
        return null;
    }

    @Override
    public Shape getShape(ShapeType shapeType) {
        if (shapeType == null){
            return null;
        }
        if (shapeType == ShapeType.CIRCLE){
            return new Circle();
        }
        if (shapeType == ShapeType.RECTANGLE){
            return new Rectangle();
        }
        if (shapeType == ShapeType.SQUARE){
            return new Square();
        }
        return null;
    }
}
