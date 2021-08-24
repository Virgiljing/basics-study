package mycollection;

public class MyLinkedList<T> {
	private Node<T> first;
	private Node<T> last;
	private int size;
	public void add(T t) {
		if (first==null) {
			Node<T> n = new Node<>();
			n.setPrevious(null);
			n.setT(t);
			n.setNext(null);
			first = n;
			last = n;
		}else {
			Node<T> n = new Node<>();
			n.setPrevious(last);
			n.setT(t);
			n.setNext(null);
			last.setNext(n);
			last = n;
		}
		size ++;
	}
	public int size() {
		return size;
	}
	public T get(int index) {
		Node<T> temp = null;
		if (index>=size&&index<0) {
			throw new IndexOutOfBoundsException("数组越界异常"+index);
		}
		if (first==null) {
			throw new NullPointerException("空指针异常，集合未初始化");
		}else {
			temp = first;
			for(int i=0;i<index;i++) {
				temp = temp.getNext();
			}
		}
		return temp.getT();
	}
	private Node<T> getNode(int index){
		Node<T> temp = null;
		if (index>=size&&index<0) {
			throw new IndexOutOfBoundsException("数组越界异常"+index);
		}
		if (first==null) {
			throw new NullPointerException("空指针异常，集合未初始化");
		}else {
			temp = first;
			for(int i=0;i<index;i++) {
				temp = temp.getNext();
			}
		}
		return temp;
	}
	public void remove(int index) {
		Node<T> temp = null;
		if (first!=null) {
			temp = first;
			for(int i=0;i<index;i++) {
				temp = temp.getNext();
			}
		}
		if (temp!=null) {
			Node<T> up = temp.getPrevious();
			Node<T> down = temp.getNext();
			up.setNext(down);
			down.setPrevious(up);
			size --;
		}
	}
	public void add(int index,T t) {
		Node<T> temp = this.getNode(index);
		Node<T> newNode = new Node<>();
		newNode.setT(t);
		if (temp!=null) {
			Node up = temp.getPrevious();
			up.setNext(newNode);
			newNode.setPrevious(up);
			newNode.setNext(temp);
			temp.setPrevious(newNode);
			size ++;
		}
	}
}
