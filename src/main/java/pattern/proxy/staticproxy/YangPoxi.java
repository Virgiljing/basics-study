package pattern.proxy.staticproxy;

public class YangPoxi implements IKindWomen {

	@Override
	public void happyWithMan() {
		System.out.println("阎婆惜 is happying。?");
	}

	@Override
	public double collect(double money) {
		System.out.println("阎婆惜收到了"+money);
		return money;
	}

}
