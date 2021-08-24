package pattern.adapter;

/**
 * @author 张航铭
 *  适配器本身(对象组合的方式)
 */
public class Adpater2 implements Target{
    private Adaptee adaptee;
	@Override
	public void handleReq() {
		adaptee.request();
	}
	public Adpater2(Adaptee adaptee) {
		
		this.adaptee = adaptee;
	}

}
