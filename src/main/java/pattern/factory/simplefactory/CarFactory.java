package pattern.factory.simplefactory;

/**
 * @author 张航铭
 * 简单工厂模式
 * 无法满足开闭原则
 *
 */
public class CarFactory {
	public static Car createCar(String type) {
		if ("奔驰".equals(type)) {
			return new BenChi();
		}else if ("宝马".equals(type)) {
			return new BaoMa();
		}else {
			return null;
		}
	}
}
