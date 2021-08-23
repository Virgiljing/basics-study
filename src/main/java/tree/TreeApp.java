package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreeApp {
	public static void main(String[] args) throws IOException  {
		int value;
		Tree theTree = new Tree();
		theTree.insert(30, 1.3);
		theTree.insert(33, 1.3);
		theTree.insert(66, 1.3);
		theTree.insert(11, 1.3);
		theTree.insert(73, 1.3);
		theTree.insert(23, 1.3);
		theTree.insert(46, 1.3);
		theTree.insert(44, 1.3);
		theTree.insert(32, 1.3);
		theTree.insert(56, 1.3);
		theTree.insert(89, 1.3);
		theTree.displayTree();
//		while (true) {
//			System.out.println("Enter first letter of show,insert,find,delete,or traverse:");
//			char choice = getChar();
//			switch (choice) {
//			case 'i':
//				System.out.println("Enter your insert number:");
//				int id = getInt();
//				theTree.insert(id, id+0.9);
//				break;
//			case 's':
//				theTree.displayTree();
//				break;
//			case 'f':
//				System.out.println("Enter you find number:");
//				int find = getInt();
//				theTree.find(find);
//				break;
//			case 'd':
//				System.out.println("Enter you delete number:");
//				int dele = getInt();
//				theTree.delete(dele);
//			case 't':
//				System.out.println("Enter you traverse number:");
//				int tra = getInt();
//				theTree.traverse(tra);
//			default:
//				break;
//			}
//		}
	}
	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}
	public static char getChar() throws IOException {
		return getString().charAt(0);
	}
	public static int getInt() throws NumberFormatException, IOException {
		return Integer.parseInt(getString());
	}
}
