package pattern2.chainOfResp;

/**
 * @author 张航铭
 * 责任链
 * Java中异常处理
 * JavaScript事件的冒泡和捕获，Java中事件的处理采用的是管擦着模式
 * Servlet开发中，过滤器的链式处理
 * Struts2中，拦截器的掉用也是典型的责任链模式
 *
 */
public class Client {
	public static void main(String[] args) {
		Leader a = new Director("张三");
		Leader b = new Manager("李四");
		Leader c = new GeneralManager("王五");
		a.setNextLeader(b);
		b.setNextLeader(c);
		LeaveRequest request = new LeaveRequest("赵六", 11, "回英国老家探亲");
		a.handlerRequest(request);
	}
}
