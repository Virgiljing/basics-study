package pattern.factory.simplefactory;

public class CarFactory2 {
	public static Car createBenChi() {
		return new BenChi();
	}
	public static Car createBaoMa() {
		return new BaoMa();
	}
}
