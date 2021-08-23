package pattern.createpattern.builder;

/**
 * Pepsi
 *
 * @author virgilin
 * @date 2019/3/15
 */
public class Pepsi extends ColdDrink {
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 35.0f;
    }
}
