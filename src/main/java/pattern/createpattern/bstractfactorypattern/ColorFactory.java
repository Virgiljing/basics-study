package pattern.createpattern.bstractfactorypattern;

/**
 * ColorFactory
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(ColorType colorType) {
        if (colorType == null){
            return null;
        }
        if (colorType == ColorType.BLUE){
            return new Blue();
        }
        if (colorType == ColorType.GREEN){
            return new Green();
        }
        if (colorType == ColorType.RED){
            return new Red();
        }
        return null;
    }

    @Override
    public Shape getShape(ShapeType shapeType) {
        return null;
    }
}
