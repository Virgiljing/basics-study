package pattern.createpattern.builder;

/**
 * ChickenBurger
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 50.5f;
    }
}
