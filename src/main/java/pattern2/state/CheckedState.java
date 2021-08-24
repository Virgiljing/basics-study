package pattern2.state;

/**
 * @author 张航铭
 *  状态模式
 */
public class CheckedState implements State{

	@Override
	public void handle() {
		System.out.println("房间已预定！！！");
	}
}
