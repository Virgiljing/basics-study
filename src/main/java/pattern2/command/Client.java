package pattern2.command;

/**
 * @author 张航铭
 * Strut2,action的整个掉用过程就有命令模式
 * 数据库的事务机制的底层实现
 * 命令的撤销和恢复
 *
 */
public class Client {
	public static void main(String[] args) {
		Command c = new ConcreteCommand(new Receiver());
		Invoke invoke = new Invoke(c);
		invoke.call();
	}
}
