package pattern.builder;

/**
 * @author 张航铭
 *	建造者模式
 */
public class Client {
	public static void main(String[] args) {
		AirShipDirector director = new MyAirShipDirector(new MyAirShipBuilder());
		AirShip ship = director.directAirShip();
		ship.launch();
	}
}
