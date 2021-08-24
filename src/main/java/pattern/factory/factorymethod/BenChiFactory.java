package pattern.factory.factorymethod;

/**
 * @author 张航铭
 * 工厂方法模式
 *
 */
public class BenChiFactory implements CarFactory{

	@Override
	public Car createCar() {
		return new BenChi();
	}
}
