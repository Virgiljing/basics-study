package pattern.factory.factorymethod;

public class Client {
	public static void main(String[] args) {
		Car car = new BaoMaFactory().createCar();
		Car car2 = new BenChiFactory().createCar();
		car.run();
		car2.run();
	}
}
