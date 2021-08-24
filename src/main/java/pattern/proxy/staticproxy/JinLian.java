package pattern.proxy.staticproxy;

public class JinLian implements IKindWomen {

	@Override
	public void happyWithMan() {
		System.out.println("金莲 is happying。。。");
	}

	@Override
	public double collect(double money) {
		System.out.println("金莲收到费用："+money);
		return money;
	}

}
