package pattern2.observer2;

import java.util.Observable;

public class ConcreteSubject extends Observable{
	private int state;
	public void set(int s) {
		state = s;//目标对象状态发生了改变
		setChanged();//表示目标对象已经做了更改
		notifyObservers();//通知所有观察者
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
