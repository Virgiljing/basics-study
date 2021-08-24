package pattern.compsite;

/**
 * @author 张航铭
 * 组合模式
 * 开发中的应用场景
 *   -操作系统的资源管理器
 *   -GUI中的容器层次图
 *   -XML文件解析
 *   -OA系统中，组织结构的处理
 *   -Juint单元测试框架
 *   处理树形结构
 *
 */
public class Client {
	public static void main(String[] args) {
		AbstractFile f2,f3,f4,f5,f6;
		Folder f1 = new Folder("我的收藏");
		f2 = new ImageFile("图片.jpg");
		f3 = new TextFile("hello.txt");
		f1.add(f2);
		f1.add(f3);
		
		
		Folder f11 = new Folder("电影");
		f4 = new VideoFile("笑傲江湖.avi");
		f5 = new VideoFile("神雕侠侣.avi");
		f11.add(f4);
		f11.add(f5);
		f1.add(f11);
		f1.killVirus();

	}
}
