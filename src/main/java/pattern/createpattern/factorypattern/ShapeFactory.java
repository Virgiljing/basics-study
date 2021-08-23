package pattern.createpattern.factorypattern;

/**
 * ShapeFactory
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class ShapeFactory {
    public Shape getShape(ShapeType shapeType){
        if (shapeType == null){
            return null;
        }
        if(shapeType.equals(ShapeType.CIRCLE)){
            return new Circle();
        } else if(shapeType.equals(ShapeType.RECTANGLE)){
            return  new Rectangle();
        } else if(shapeType.equals(ShapeType.SQUARE)){
            return new Square();
        }
        return null;
    }
}
