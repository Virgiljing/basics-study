package pattern2.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CareTaker {
	private EmpMemento memento;
	
	//可以进行多次备忘
	private List<EmpMemento> list = new ArrayList<>();
	//栈先进先出进行备忘录保存
	private Stack<EmpMemento> stack = new Stack<>();
	public EmpMemento getMemento() {
		return memento;
	}

	public void setMemento(EmpMemento memento) {
		this.memento = memento;
	}
	
}
