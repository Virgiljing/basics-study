package pattern.factory.abstractFactory;

/**
 * @author 张航铭
 *  抽象工厂模式
 *  不可以增加产品，可以增加产品族
 */
public interface CarFactory {
	Engine createEngine();
	Seat createSeat();
	Tyre createTyre();
}

class LuxuryCarFactory implements CarFactory{

	@Override
	public Engine createEngine() {
		return new LuxuryEngine();
	}

	@Override
	public Seat createSeat() {
		return new LuxurySeat();
	}

	@Override
	public Tyre createTyre() {
		return new LuxuryTyre();
	}
	
}

class LowCarFactory implements CarFactory{
	
	@Override
	public Engine createEngine() {
		return new LowEngine();
	}
	
	@Override
	public Seat createSeat() {
		return new LowSeat();
	}
	
	@Override
	public Tyre createTyre() {
		return new LowTyre();
	}
	
}