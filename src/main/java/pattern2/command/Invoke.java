package pattern2.command;

public class Invoke {
	private Command command;//也可以通过容器List<Command> 容纳很多命令，进行批处理，数据库底层的事务管理就是类似的结构
	public Invoke(Command command) {
		super();
		this.command = command;
	}
	/**
	 * 真正命令执行前或执行后做出相应操作
	 */
	public void call() {
		command.execute();
	}
}
