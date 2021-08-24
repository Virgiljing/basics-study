package pattern.compsite;
//组合模式
/**
 * 抽象组件
 */
public interface Component {
	void operation();
}

/**
 * 叶子组件
 */
interface Leaf extends Component{
	
}
/**
 *	容器构建角色
 */
interface Composite extends Component{
	void add (Component c);
	void remove(Component c);
	Component getChild(int index);
}