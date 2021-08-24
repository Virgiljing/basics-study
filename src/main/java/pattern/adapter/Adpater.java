package pattern.adapter;

/**
 * @author 张航铭
 *  适配器本身(类适配器方式)
 */
public class Adpater extends Adaptee implements Target{

	@Override
	public void handleReq() {
		super.request();
	}

}
