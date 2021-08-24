package pattern.facade;

public class 中国工商银行 implements 银行 {
	@Override
	public void openAccount() {
		System.out.println("在中国工商银行开户！");
	}
}
