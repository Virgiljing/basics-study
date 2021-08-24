package pattern.builder;

public class MyAirShipBuilder implements AirShipBuilder {

	@Override
	public Engine builderEngine() {
		System.out.println("构建发动机");
		return new Engine("发动机");//可以和工厂模式或单例模式结合
	}

	@Override
	public OrbitalModule builderOrbitalModule() {
		System.out.println("构建轨道舱");
		return new OrbitalModule("轨道舱");
	}

	@Override
	public EscapeTower builderEscapeTower() {
		System.out.println("构建逃生舱");
		return new EscapeTower("逃生舱");
	}

}
