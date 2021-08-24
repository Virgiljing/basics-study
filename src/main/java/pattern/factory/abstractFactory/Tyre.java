package pattern.factory.abstractFactory;

public interface Tyre {
	void revolve();
}

class LowTyre implements Tyre{

	@Override
	public void revolve() {
		System.out.println("旋转磨损");
	}
	
}

class LuxuryTyre implements Tyre{
	
	@Override
	public void revolve() {
		System.out.println("旋转磨损");
	}
	
}
