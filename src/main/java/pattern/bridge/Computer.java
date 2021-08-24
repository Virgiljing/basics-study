package pattern.bridge;

public interface Computer {
	void sale();
}

class Laptop implements Computer{

	@Override
	public void sale() {
		System.out.println("销售笔记本");
	}
	
}
class Desktop implements Computer{
	
	@Override
	public void sale() {
		System.out.println("销售台式机");
	}
	
}
class Pad implements Computer{
	
	@Override
	public void sale() {
		System.out.println("销售平板电脑");
	}
	
}
class ShenZhouLaptop extends Laptop{
	@Override
	public void sale() {
		System.out.println("销售神州笔记本");
	}
}
class ShenZhouDesktop extends Laptop{
	@Override
	public void sale() {
		System.out.println("销售神州台式机");
	}
}
class ShenZhouLapPad extends Laptop{
	@Override
	public void sale() {
		System.out.println("销售神州平板");
	}
}
class DellLaptop extends Laptop{
	@Override
	public void sale() {
		System.out.println("销售Dell笔记本");
	}
}
class DellDesktop extends Laptop{
	@Override
	public void sale() {
		System.out.println("销售Dell台式机");
	}
}
class DellLapPad extends Laptop{
	@Override
	public void sale() {
		System.out.println("销售Dell平板");
	}
}
