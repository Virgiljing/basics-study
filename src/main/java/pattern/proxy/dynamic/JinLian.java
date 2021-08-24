package pattern.proxy.dynamic;

public class JinLian implements IKindWomen {

	@Override
	public void happyWithMan() {
		System.out.println("金莲 is happying。");
	}

	@Override
	public double collect(double money) {
		System.out.println("金莲收到"+money);
		return money;
	}

}
