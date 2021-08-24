package pattern2.mediator;

import java.util.HashMap;
import java.util.Map;

public class President implements Mediator {
	private Map<String, Department> map = new HashMap<>();
	@Override
	public void register(String name, Department d) {
		map.put(name, d);
	}

	@Override
	public void command(String dname) {
		map.get(dname).selfAction();
	}

}
