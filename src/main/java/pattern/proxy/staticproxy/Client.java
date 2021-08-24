package pattern.proxy.staticproxy;

public class Client {
	public static void main(String[] args) {
		IKindWomen women = new JinLian();
		WangPo wangPo = new WangPo(women);
		wangPo.happyWithMan();
		wangPo.collect(600);
		IKindWomen women2 = new YangPoxi();
		WangPo wangPo2 = new WangPo(women2);
		wangPo2.happyWithMan();
		wangPo2.collect(500);
	}
}
