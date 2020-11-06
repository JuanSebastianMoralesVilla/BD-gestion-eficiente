package data_structures;

public class AVLTree<K extends Comparable<K>,E> extends BinarySearchTree <K,E> {
	
	public AVLTree() {
		super();
	}
	
	@Override
	public void insert(K key,E element) {
		Node<K,E> newNode = new Node<K,E>(key,element);	
		if(getRoot() == null) {
			setRoot(newNode);
		}else {
			super.insert(newNode, getRoot());
		}
		setWeight(getWeight() + 1);
		balance(newNode);
	}
	
	@Override
	public boolean deleteValue(K key) {
		if(getRoot()!=null && getRoot().getKey().toString().compareTo(key.toString())==0) {
			Node<K,E> aux = deleteValue(getRoot(),key);
			setWeight(getWeight() - 1);
			balance(aux);
			return true;
		}else {
			Node<K,E> aux = deleteValue(getRoot(),key);
			if(aux!=null) {
				setWeight(getWeight() - 1);
				balance(aux);
				return true;
			}
		}
		return false;
	}
	

	private Node<K,E> deleteValue(Node<K,E> root,K value) {
		if(root==null) {
			return null;
		}
		if(value.toString().compareTo(root.getKey().toString())>0) {
				Node<K,E> aux = deleteValue(root.getRight(), value);
				if(aux!=null) {
					if(root.getRight()!=null && root.getRight().getHeight()<root.getHeight()-1) {
						root.setHeight(root.getHeight()-1);
					}
				}
				return aux;
				
		}else if(value.toString().compareTo(root.getKey().toString())<0) {
				Node<K,E> aux = deleteValue(root.getLeft(),value);
				if(aux!=null) {
					if(root.getLeft()!=null && root.getLeft().getHeight()<root.getHeight()-1) {
						root.setHeight(root.getHeight()-1);
					}
				}
				return aux;	
		}else {
			if(root.getLeft()!=null && root.getRight()!=null) {
				return deleteTreeTwoSons(root);	
				
			}else if(root.getLeft()!=null) {
				return deleteTreeOneSon(root);
				
			}else if(root.getRight()!=null) {
				return deleteTreeOneSon(root);
				
			}else {
				return deleteTreeNoSons(root);
			}
		}	
	}
	
	private Node<K,E> deleteTreeOneSon(Node<K,E> root) {
		if(root.getLeft()!=null) {
			Node <K,E>aux=root.getLeft();
			Node <K,E> p=root.getParent();
			if(p.getRight()==root) {
				p.setRight(aux);
			}else {
				p.setLeft(aux);
			}
			
			aux.setParent(p);
			return aux;
		}else if(root.getRight()!=null) {
			Node <K,E>aux=root.getRight();
			Node <K,E> p=root.getParent();
			if(p.getRight()==root) {
				p.setRight(aux);
			}else {
				p.setLeft(aux);
			}
			aux.setParent(p);
			return aux;
		}
		return null;
	}
	private Node<K,E> deleteTreeTwoSons(Node<K,E> root) {
		Node<K,E> minRightValue = minimunValue(root.getRight());
		Node<K,E> result = minRightValue.getParent();
		if(minRightValue.getRight()!=null) {
			Node<K,E> temp =minRightValue;
			result = deleteTreeOneSon(minRightValue);
			if(root==this.getRoot()) {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				root.getLeft().setParent(temp);
				root.getRight().setParent(temp);
				int height1 = 1;
				int height2 = 1;
				if(temp.getLeft()!=null ) {
					height1 = temp.getLeft().getHeight();
				}
				if(temp.getRight()!=null ) {
					height2 = temp.getRight().getHeight();
				}
				root.setHeight(Math.max(height1, height2)+1);
				setRoot(temp);
				temp.setParent(null);
				
			}else {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				Node<K,E> pop = root.getParent();
				if(pop.getLeft()==root) {
					pop.setLeft(temp);
				}else if(pop.getRight()==root) {
					pop.setRight(temp);
				}
				temp.setParent(pop);
				if(root.getLeft()!=null ) {
					root.getLeft().setParent(temp);
				}
				if(root.getRight()!=null) {
					root.getRight().setParent(temp);
				}
				int height1 = 1;
				int height2 = 1;
				if(temp.getLeft()!=null ) {
					height1 = temp.getLeft().getHeight();
				}
				if(temp.getRight()!=null ) {
					height2 = temp.getRight().getHeight();
				}
				temp.setHeight(Math.max(height1, height2)+1);
				
			}
			if(result==root) {
				result = temp;
			}
		}else {
			Node<K,E> temp =minRightValue;
			deleteTreeNoSons(minRightValue);
			if(root==this.getRoot()) {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				if(root.getRight()!=null) {
					root.getRight().setParent(temp);
				}
				if(root.getLeft()!=null) {
					root.getLeft().setParent(temp);
				}
				setRoot(temp);
				temp.setParent(null);
				
			}else {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				Node<K,E> pop = root.getParent();
				if(pop.getLeft()==root) {
					pop.setLeft(temp);
				}else if(pop.getRight()==root) {
					pop.setRight(temp);
				}
				temp.setParent(pop);
				if(temp.getLeft()!=null) {
					temp.getLeft().setParent(temp);
				}
				if(temp.getRight()!=null) {
					temp.getRight().setParent(temp);
				}
			}
			if(result==root) {
				result = temp;
			}
			
		}
		return result;
	}
	
	private Node<K,E> deleteTreeNoSons(Node<K,E> node) {
		Node<K,E> parent = node.getParent();
		if(node == this.getRoot()) {
			node = null;
		}else {
			
			if(parent.getLeft()==node) {
				parent.setLeft(null);
			}else {
				parent.setRight(null);
			}
		}
		node.setParent(null);
		return parent;
	}
	
	public int balanceFactor (Node<K,E> node) {
		if(node!=null) {
			int right = height(node.getRight());
			int left = height(node.getLeft());
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
		Node<K,E> parent = nodeRight.getParent();
		if(balanceFactor==1 || balanceFactor==0) {
			leftRotate(nodeRight.getParent());
		}else{
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
		if(left.getRight()!=null){
			Node <K,E> leftRightTree = left.getRight();
			node.setLeft(leftRightTree);
			leftRightTree.setParent(node);
		}else {
			node.setLeft(null);
		}
		left.setRight(node);
		node.setParent(left);
		left.setParent(parent);
		
		if(parent!=null && node==parent .getLeft()) {
			parent.setLeft(left);
		}else if(parent!=null && node==parent.getRight()) {
			parent.setRight(left);
		}else {
			setRoot(left);
			left.setParent(null);
		}
		int height1 = 0;
		int height2 = 0;
		if(node.getLeft()!=null) {
			height1 = node.getLeft().getHeight();
		}
		if(node.getRight()!= null ){
			height2 = node.getRight().getHeight();
		}
		node.setHeight(Math.max(height1, height2)+1);
		height1 = 0;
		height2 = node.getHeight();
		if(left.getLeft()!=null) {
			height1 = left.getLeft().getHeight();
		}
		left.setHeight(Math.max(height1, height2)+1);
		
	}
	
	public void leftRotate(Node <K,E> node) {
		Node<K,E> parent =node.getParent();
		Node <K,E> right = node.getRight();
		if(right.getLeft()!=null){
			Node <K,E> rightLeftTree = right.getLeft();
			node.setRight(rightLeftTree);
			rightLeftTree.setParent(node);
		}else {
			node.setRight(null);
		}
		right.setLeft(node);
		node.setParent(right);
		right.setParent(parent);
		if(parent!=null && node==parent .getLeft()) {
			parent.setLeft(right);
		}else if(parent!=null && node==parent.getRight()) {
			parent.setRight(right);
		}else {
			setRoot(right);
			right.setParent(null);
		}
		int height1 = 0;
		int height2 = 0;
		if(node.getLeft()!=null) {
			height1 = node.getLeft().getHeight();
		}
		if(node.getRight()!= null ){
			height2 = node.getRight().getHeight();
		}
		node.setHeight(Math.max(height1, height2)+1);
		height1 = 0;
		height2 = node.getHeight();
		if(right.getRight()!=null) {
			height1 = right.getRight().getHeight();
		}
		right.setHeight(Math.max(height1, height2)+1);
	}
	
}	
