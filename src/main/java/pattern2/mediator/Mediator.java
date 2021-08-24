package pattern2.mediator;

public interface Mediator {
	void register(String name,Department d);
	void command(String dname);
}
