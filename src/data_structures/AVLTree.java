package data_structures;

public class AVLTree<K extends Comparable<K>,E> extends BinarySearchTree <K,E> {
	
	public AVLTree() {
		super();
	}
	
	@Override
	public void insert(E element,K key) {
		Node<K,E> newNode = new Node<K,E>(key,element);	
		if(getRoot() == null) {
			setRoot(newNode);
		}else {
			super.insert(newNode, getRoot());
		}
		balance(newNode);
	}
	
	@Override
	public boolean deleteValue(K key) {
		return false;
		
	}
	
	public int balanceFactor (Node<K,E> node) {
		if(node!=null) {
			int right = super.height(node.getRight());
			int left = super.height(node.getLeft());
			return right - left;
		}
		return 0;
	}
	
	public void balance(Node<K,E> node) {
		if(node!=null) {
			int balanceFactor = balanceFactor(node);
			Node<K,E> parent = node.getParent();
			if(balanceFactor>1) {
				rightCases(node.getRight());
			}else if(balanceFactor<-1) {
				leftCases(node.getLeft());
			}
			balance(parent);
		}
	}
	
	public void rightCases(Node<K,E> nodeRight) {
		int balanceFactor = balanceFactor(nodeRight);
		if(balanceFactor==1 || balanceFactor==0) {
			leftRotate(nodeRight);
		}else{
			Node<K,E> parent = nodeRight.getParent();
			rightRotate(nodeRight);
			leftRotate(parent);
		}
	}
	
	public void leftCases(Node<K,E> nodeLeft) {
		int balanceFactor = balanceFactor(nodeLeft);
		if(balanceFactor==-1 || balanceFactor==0) {
			rightRotate(nodeLeft.getParent());
		}else{
			Node<K,E> parent = nodeLeft.getParent();
			leftRotate(nodeLeft);
			rightRotate(parent);
		}
	}

	public void rightRotate(Node <K,E> node) {	
		Node<K,E> parent =node.getParent();
		Node <K,E> left = node.getLeft();
		if(left.getRight()!=null) {
			Node <K,E> leftRightTree = left.getRight();
			node.setLeft(leftRightTree);
			leftRightTree.setParent(node);
		}else {
			node.setLeft(null);
		}
		left.setRight(node);
		node.setParent(left);
		left.setParent(parent);
		
		if(parent!=null && node==parent.getLeft()) {
			parent.setLeft(left);
		}else if(parent!=null && node==parent.getRight()) {
			parent.setRight(left);
		}else {
			setRoot(left);
		}
	}
	
	public void leftRotate(Node <K,E> node) {
		Node <K,E> right = node.getRight();
		Node <K,E> parent = node.getParent();
		if(parent!=null) {
			
			if(parent.getRight().equals(node)) {
				parent.setRight(right);
				right.setParent(parent);
			}else {
				parent.setLeft(right);
				right.setParent(parent);
			}
		}else{
			setRoot(right);
		}
		if(right.getLeft()!=null) {
			node.setRight(right.getLeft());
			right.getLeft().setParent(node);
		}
		right.setLeft(node);
		node.setParent(right);
	}
	
}	
