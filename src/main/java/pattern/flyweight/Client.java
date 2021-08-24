package pattern.flyweight;

/**
 * @author 张航铭
 * 享元模式
 *   -极大减少内存中对象的数量
 *   -
 *
 */
public class Client {

	public static void main(String[] args) {
		ChessFlyWeight chess1 = ChessFlyWeightFactory.getChess("黑色");
		ChessFlyWeight chess2 = ChessFlyWeightFactory.getChess("黑色");
		System.out.println(chess1);
		System.out.println(chess2);
		System.out.println("增加外部状态处理=======");
		chess1.display(new Coordinate(5,6));
		chess2.display(new Coordinate(88,60));
	}

}
