package pattern2.observer;

public class ConcreteSubject extends Subject{
	private int state;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
		//目标对象发生变化通知所有观察者
		this.notifyAllObservers();
	}
}
