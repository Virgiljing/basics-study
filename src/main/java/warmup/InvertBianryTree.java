package warmup;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBianryTree {
	//先序遍历
	public TreeNode invertTree(TreeNode root) {
		if (root!=null) {
			TreeNode t = root.left;
			root.left = root.right;
			root.right = t;
			invertTree(root.left);
			invertTree(root.right);
			return root;
		}else {
			return null;
		}
		
	}
	//后序遍历
	public TreeNode invertTree1(TreeNode root) {
		if (root!=null) {
			invertTree(root.left);
			invertTree(root.right);
			TreeNode t = root.left;
			root.left = root.right;
			root.right = t;	
			return root;
		}else {
			return null;
		}
		
	}
	//中序遍历
	public TreeNode invertTree2(TreeNode root) {
		if (root!=null) {
			invertTree(root.left);	
			TreeNode t = root.left;
			root.left = root.right;
			root.right = t;	
			invertTree(root.left);
			return root;
		}else {
			return null;
		}
		
	}
	//层序遍历
	public TreeNode invertTree_bst(TreeNode root) {
		if (root!=null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
			while (!queue.isEmpty()) {
				TreeNode p = queue.poll();
				TreeNode t = p.left;
				p.left = p.right;
				p.right = t;
				if (p.left!=null) {
					queue.offer(p.left);
				}
				if (p.right!=null) {
					queue.offer(p.right);
				}
			}
			
			return root;
		}else {
			return null;
		}
		
	}
}
