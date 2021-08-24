package pattern.builder;

public class MyAirShipDirector implements AirShipDirector {
	private AirShipBuilder builder;
	
	public MyAirShipDirector(AirShipBuilder builder) {
		super();
		this.builder = builder;
	}

	@Override
	public AirShip directAirShip() {
		Engine engine = builder.builderEngine();
		EscapeTower escapeTower = builder.builderEscapeTower();
		OrbitalModule orbitalModule = builder.builderOrbitalModule();
		AirShip ship = new AirShip();
		ship.setEngine(engine);
		ship.setEscapeTower(escapeTower);
		ship.setOrbitalModule(orbitalModule);
		return ship;
	}

}
