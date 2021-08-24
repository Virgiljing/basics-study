package pattern2.strategy;

/**
 * @author 张航铭
 * 开发场景
 * -JAVASE中的GUI编程，布局管理
 * -Spring框架中，Resource接口，资源访问策略
 * -javax.servlet.http.HttpServlet#service()
 *
 */
public class Client {
	public static void main(String[] args) {
		Strategy s1 = new OldCustomerFewStrategy();
		Strategy s2 = new NewCustomerFewStrategy();
		Context context = new Context(s2);
		context.pringPrice(100);
	}
}
