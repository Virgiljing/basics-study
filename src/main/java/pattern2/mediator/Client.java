package pattern2.mediator;

/**
 * @author 张航铭
 * 中介者模式
 * -解耦多个同事对象之间的交互关系，每个对象都持有中介者对象的引用
 *  只跟中介者打交道。我们通过中介者对象统一管理这些交互者的关系
 *
 */
public class Client {
	public static void main(String[] args) {
		Mediator m = new President();
		Market market = new Market(m);
		Development development = new Development(m);
		Finacial finacial = new Finacial(m);
		market.selfAction();
		market.outAction();

		development.selfAction();
		development.outAction();

		finacial.selfAction();
		finacial.outAction();
		
	}
}
