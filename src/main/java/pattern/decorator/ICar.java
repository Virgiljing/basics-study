package pattern.decorator;

public interface ICar {
	void move();
}
class Car implements ICar{
	protected ICar car;
	
	@Override
	public void move() {
		System.out.println("陆地上跑");
	}
	
}
class SupperCar implements ICar{
	protected ICar car;
	public SupperCar(ICar car) {
		super();
		this.car = car;
	}
	@Override
	public void move() {
		car.move();
	}
	
}
class FlyCar extends SupperCar{
	
	public FlyCar(ICar car) {
		super(car);
	}
	public void fly() {
		System.out.println("天上飞！");
	}
	@Override
	public void move() {
		car.move();
		fly();
	}
	
}
class WaterCar extends SupperCar{
	
	public WaterCar(ICar car) {
		super(car);
	}
	public void swim() {
		System.out.println("水上游！");
	}
	@Override
	public void move() {
		car.move();
		swim();
	}
	
}
class AICar extends SupperCar{
	
	public AICar(ICar car) {
		super(car);
	}
	public void autoMove() {
		System.out.println("自己跑！");
	}
	@Override
	public void move() {
		car.move();
		autoMove();
	}
	
}