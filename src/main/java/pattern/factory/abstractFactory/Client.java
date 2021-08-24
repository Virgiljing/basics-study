package pattern.factory.abstractFactory;

public class Client {
	public static void main(String[] args) {
		LuxuryCarFactory factory = new LuxuryCarFactory();
		Engine engine = factory.createEngine();
		Seat seat = factory.createSeat();
		Tyre tyre = factory.createTyre();
		engine.start();
		engine.run();
		seat.massage();
		tyre.revolve();
	}
}
