package daliyPractice;

import java.io.UnsupportedEncodingException;

public class Bianma {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String name = "张航铭";
		byte[] bytes = name.getBytes("UTF-8");
		String string = new String(bytes, "ISO-8859-1");
		System.out.println(string);
	}
}
