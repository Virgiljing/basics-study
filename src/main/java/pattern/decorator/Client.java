package pattern.decorator;

/**
 * @author 张航铭
 * 装饰模式（decorator）
 * 开发中使用的场景
 *   -IO中输入流和输出流的设计
 *   -Swing包中图形界面构件功能
 *   -Sevlet API中提供的request对象的Decorator设计模式的默认实现类
 *    HttpServletRequestWrapper , HttpServletResponseWrapper类
 *    增强了request对象的功能
 *   -Struts2中，request，response，session对象的处理
 */
public class Client {

	public static void main(String[] args) {
		Car car = new Car();
		car.move();
		FlyCar flyCar = new FlyCar(car);
		flyCar.move();
		
		System.out.println("-----------------");
		WaterCar waterCar = new WaterCar(flyCar);
		waterCar.move();
	}

}
