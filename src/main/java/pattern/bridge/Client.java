package pattern.bridge;

/**
 * @author 张航铭
 *   桥接模式
 *   -JDBC驱动程序
 *   -AWT中的Peer架构
 *   -银行日志管理
 *   -人力资源系统中的奖金计算
 *   -OA系统中的消息处理
 *
 */
public class Client {
	public static void main(String[] args) {
		Brand levono = new Lenovo();
		Computer2 c = new Laptop2(levono);
		c.sale();
	}
}
