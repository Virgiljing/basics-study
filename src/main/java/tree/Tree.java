package tree;

import java.util.Stack;

public class Tree {
	private Node root;
	public Tree() {
		root = null;
	}
	/**
	 * 增加节点
	 * @param id
	 * @param dd
	 */
	public void insert(int id,double dd) {
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;
		if (root == null) {
			root = newNode;
		}else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (id < current.iData) {
					parent.leftChild = newNode;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				}else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
	/**
	 * 删除节点
	 * @param key
	 * @return
	 */
	public boolean delete(int key) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		while (current.iData!=key) {
			parent = current;
			if (key < current.iData) {
				isLeftChild = true;
				current = current.leftChild;
			}else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if (current == null) {
				return false;
			}
		}
		if (current.leftChild==null&&current.rightChild==null) {
			if (current == root) {
				root = null;
			}else if (isLeftChild) {
				parent.leftChild = null;
			}else {
				parent.rightChild = null;
			}
		}else if (current.rightChild == null) {
			if (current == root) {
				root = current.leftChild;
			}else if (isLeftChild) {
				parent.leftChild = current.leftChild;
			}else {
				parent.rightChild = current.leftChild;
			}
		}else if (current.leftChild == null) {
			if (current == root) {
				root = current.rightChild;
			}else if (isLeftChild) {
				parent.leftChild = current.rightChild;
			}else {
				parent.rightChild = current.rightChild;
			}
		}else {
			Node successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			}else if (isLeftChild) {
				parent.leftChild = successor;
			}else {
				parent.rightChild = successor;
			}
			successor.leftChild = current.leftChild;
		}
		return true;
	}
	/**
	 * 向右子节点下诏继承者节点
	 * @param delNode
	 * @return
	 */
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		if (successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}
	/**
	 * 查找结点
	 * @param key
	 * @return
	 */
	public Node find(int key) {
		Node current = root;
		while (current.iData!=key) {
			if (key < current.iData) {
				current = current.leftChild;
			}else {
				current = current.rightChild;
			}
			if (current == null) {
				return null;
			}
		}
		return current;
	}
	/**
	 * 调用遍历方式
	 * @param traverseType
	 */
	public void traverse(int traverseType) {
		switch (traverseType) {
		case 1:
			//从上至下，从左至右
			System.out.println("Preorder traversal:");
			preOrder(root);
			break;
		case 2:
			//从下到上，从左至右（从下到大）
			System.out.println("InOrder traversal:");
			inOrder(root);
			break;
		case 3:
			//从上至下从右至左
			System.out.println("PostOrder traversal:");
			postOrder(root);
			break;

		default:
			break;
		}
	}
	
	
	/**
	 * 从上至下从左至右遍历
	 * @param localRoot
	 */
	private void preOrder(Node localRoot) {
		if (localRoot != null) {
			System.out.print(localRoot.iData + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}
	/**
	 * 从下到上，从左至右（从下到大）
	 * @param localRoot
	 */
	private void inOrder(Node localRoot) {
		if (localRoot != null) {
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.iData + " ");
			inOrder(localRoot.rightChild);
		}
	}
	/**
	 * 
	 * @param localRoot
	 */
	private void postOrder(Node localRoot) {
		if (localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.iData + " ");
		}
	}
	
	/**
	 * 艺术的结构展示数据
	 */
	public void displayTree() {
		Stack<Node> globalStack = new Stack<>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("------------------------------------");
		while (isRowEmpty == false) {
			Stack<Node> localStack = new Stack<>();
			isRowEmpty = true;
			for (int i = 0; i < nBlanks; i++) {
				System.out.print(" ");
			}
			while (globalStack.isEmpty() == false) {
				Node temp = globalStack.pop();
				if (temp != null) {
					System.out.print(temp.iData);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if (temp.leftChild!=null||temp.rightChild!=null) {
						isRowEmpty = false;
					}
				}else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int i = 0; i < nBlanks*2-2; i++) {
					System.out.print(" ");
				}
			}
			System.out.println();
			nBlanks/=2;
			while (localStack.isEmpty()==false) {
				globalStack.push(localStack.pop());
			}
		}
		System.out.println("-----------------------------------------");
	}
}
