package pattern.facade;

public class Client {
	public static void main(String[] args) {
		RegisterFacade rf = new RegisterFacade();
		rf.register();
	}
}
