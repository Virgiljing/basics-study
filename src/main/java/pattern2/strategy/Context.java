package pattern2.strategy;

/**
 * @author 张航铭
 *	负责和具体的策略类交互
 *	这样的话，具体算法直接和客户端的调用分离
 *  spring的依赖注入，可以通过配置文件注解注入
 */
public class Context {
	private Strategy strategy;//采用当前算法

	public Context(Strategy strategy) {
		super();
		this.strategy = strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	public void pringPrice(double s) {
		System.out.println("你的报价："+strategy.getPrice(s));
	}
}
