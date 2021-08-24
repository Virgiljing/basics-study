package pattern.factory.factorymethod;

/**
 * @author 张航铭
 * 简单工厂模式
 * 无法满足开闭原则
 *
 */
public class BaoMaFactory implements CarFactory{

	@Override
	public Car createCar() {
		return new BaoMa();
	}
}
