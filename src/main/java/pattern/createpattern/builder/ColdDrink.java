package pattern.createpattern.builder;

/**
 * ColdDrink
 *
 * @author virgilin
 * @date 2019/3/14
 */
public abstract class ColdDrink implements Item {


    @Override
    public Packing packing() {
        return new Bottle();
    }

}
