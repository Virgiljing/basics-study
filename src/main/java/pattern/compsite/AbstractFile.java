package pattern.compsite;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象构建
 */
public interface AbstractFile {
	void killVirus();//杀毒
}
class ImageFile implements AbstractFile{
	private String name ;
	public ImageFile(String name) {
		super();
		this.name = name;
	}
	@Override
	public void killVirus() {
		System.out.println("---图像文件"+name+",进行查杀！");
	}
	
}
class TextFile implements AbstractFile{
	private String name ;
	public TextFile(String name) {
		super();
		this.name = name;
	}
	@Override
	public void killVirus() {
		System.out.println("---文本文件"+name+",进行查杀！");
	}
	
}
class VideoFile implements AbstractFile{
	private String name ;
	public VideoFile(String name) {
		super();
		this.name = name;
	}
	@Override
	public void killVirus() {
		System.out.println("---文本文件"+name+",进行查杀！");
	}
	
}

class Folder implements AbstractFile{
	private String name ;
	private List<AbstractFile> list = new ArrayList<>();
	
	public Folder(String name) {
		super();
		this.name = name;
	}
	public void add(AbstractFile file) {
		list.add(file);
	}
	public void remove (AbstractFile file) {
		list.remove(file);
	}
	public AbstractFile get (int index) {
		return list.get(index);
	}
	@Override
	public void killVirus() {
		System.out.println("-----文件夹"+name+",进行杀毒");
		for (AbstractFile file : list) {
			file.killVirus();
		}
	}
	
	
}