package pattern.createpattern.builder;

/**
 * Coke
 *
 * @author virgilin
 * @date 2019/3/14
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}
