package pattern.createpattern.bstractfactorypattern;

/**
 * AbstractFactory
 *
 * @author virgilin
 * @date 2019/3/14
 */
public abstract class AbstractFactory {
    public abstract Color getColor(ColorType colorType);
    public abstract Shape getShape(ShapeType shapeType);
}
