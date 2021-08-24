package pattern2.state;

/**
 * @author 张航铭
 *  状态模式
 */
public class BookedState implements State{

	@Override
	public void handle() {
		System.out.println("房间空闲，没人住！！！");
	}
}
