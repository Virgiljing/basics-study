package pattern2.command;

public class ConcreteCommand implements Command {
	private Receiver receiver;
	
	public ConcreteCommand(Receiver receiver) {
		super();
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		//真正命令执行前或执行后做出相应操作
		receiver.action();
	}

}
