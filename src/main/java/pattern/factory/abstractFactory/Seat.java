package pattern.factory.abstractFactory;

public interface Seat {
	void massage();
}

class LowSeat implements Seat{

	@Override
	public void massage() {
		System.out.println("普通座椅");
	}
}

class LuxurySeat implements Seat{
	
	@Override
	public void massage() {
		System.out.println("可以自动按摩");
	}
}
