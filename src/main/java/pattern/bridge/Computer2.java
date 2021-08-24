package pattern.bridge;

/**
 * 电脑类型的维度
 * @author 张航铭
 *
 */
public class Computer2 {
	protected Brand brand;

	public Computer2(Brand brand) {
		super();
		this.brand = brand;
	}
	public void sale() {
		brand.sale();
	}
}
class Laptop2 extends Computer2{

	public Laptop2(Brand brand) {
		super(brand);
	}
	@Override
	public void sale() {
		super.sale();
		System.out.println("销售笔记本");
	}
	
}
class Desktop2 extends Computer2{
	
	public Desktop2(Brand brand) {
		super(brand);
	}
	@Override
	public void sale() {
		super.sale();
		System.out.println("销售台式机");
	}
	
}
class Pad2 extends Computer2{
	
	public Pad2(Brand brand) {
		super(brand);
	}
	@Override
	public void sale() {
		super.sale();
		System.out.println("销售平板电脑");
	}
	
}