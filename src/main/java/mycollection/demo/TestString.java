package mycollection.demo;

public class TestString {
	public static void main(String[] args) {
		String s1 = "aa&bb&cc";
		String[] strings = s1.split("&");
		for (String string : strings) {
			System.out.println(string);
		}
	}
}
