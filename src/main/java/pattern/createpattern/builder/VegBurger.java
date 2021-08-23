package pattern.createpattern.builder;

/**
 * VegBurger
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.0f;
    }
}
