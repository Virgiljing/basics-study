package pattern.createpattern.builder;

/**
 * Burger
 *
 * @author virgilin
 * @date 2019/3/14
 */
public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }

}
