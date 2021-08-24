package pattern.facade;

public class 海淀区工商局 implements 工商局 {

	@Override
	public void checkName() {
		System.out.println("检查名字是否有冲突");
	}
}
