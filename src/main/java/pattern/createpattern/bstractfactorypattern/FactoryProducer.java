package pattern.createpattern.bstractfactorypattern;

/**
 * FactoryProducer
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class FactoryProducer {
    public static AbstractFactory getAbstracFactory(FactoryType factoryType){
        if (factoryType == FactoryType.COLOR){
            return new ColorFactory();
        }
        if (factoryType == FactoryType.SHAPE){
            return new ShapeFactory();
        }
        return null;
    }
}
