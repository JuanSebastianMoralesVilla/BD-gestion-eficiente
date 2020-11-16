package data_structures;

import java.io.Serializable;

import java.util.ArrayList;

import threads.*;


@SuppressWarnings("serial")
public class BinarySearchTree<K extends Comparable<K>,E> implements IBinarySearchTree<K, E> , Serializable{
	private Node<K,E> root;
	private boolean searching;
	private int amountSeach;
	
	public Node<K, E> getRoot() {
		return root;
	}

	public boolean isSearching() {
		return searching;
	}

	public void setSearching(boolean searching) {
		this.searching = searching;
	}

	public void setRoot(Node<K, E> root) {
		this.root = root;
	}

	private int weight;

	public BinarySearchTree() {
		root = null;
		searching = true;
		amountSeach = 0;
	}
	
	@Override
	public void insert( K key, E element) {
		Node<K,E> newNode = new Node<K,E>(key,element);	
		if(root == null) {
			root = newNode;
		}else {
			insert(newNode, root);
		}
		setWeight(getWeight() + 1);
	}
	
	protected void insert(Node<K,E> toAdd,Node<K,E> current) {
		int heightLeft = 0;
		int heightRight = 0;
		if(toAdd.getKey().toString().compareTo(current.getKey().toString()) >= 0) {
			
			if(current.getRight()==null) {
				current.setRight(toAdd);
				toAdd.setParent(current);
				
				if(current.getLeft()!= null) {
					heightLeft = current.getLeft().getHeight();
				}
				if(current.getRight()!= null) {
					heightRight = current.getRight().getHeight();
				}
				current.setHeight(Math.max(heightLeft,heightRight)+1);
			}else {
				insert(toAdd,current.getRight());
				if(current.getLeft()!= null) {
					heightLeft = current.getLeft().getHeight();
				}
				if(current.getRight()!= null) {
					heightRight = current.getRight().getHeight();
				}
				current.setHeight(Math.max(heightLeft,heightRight)+1);
			}
		}else {
			if(current.getLeft()==null) {
				current.setLeft(toAdd);
				toAdd.setParent(current);
				if(current.getLeft()!= null) {
					heightLeft = current.getLeft().getHeight();
				}
				if(current.getRight()!= null) {
					heightRight = current.getRight().getHeight();
				}
				current.setHeight(Math.max(heightLeft,heightRight)+1);
			}else {
				insert(toAdd,current.getLeft());
				if(current.getLeft()!= null) {
					heightLeft = current.getLeft().getHeight();
				}
				if(current.getRight()!= null) {
					heightRight = current.getRight().getHeight();
				}
				current.setHeight(Math.max(heightLeft,heightRight)+1);
			}
		}
	}
	
	//Delete method
	public Node<K,E> minimunValue(Node<K,E> root) {
		if(root!=null) {
			if(root.getLeft()==null) {
				return root;
			}else {
				return minimunValue(root.getLeft());		
			}
		}
		return null;
	}
	
	@Override
	public boolean deleteValue(K key) {
		if(root == null) {
			return false;
		}else {
			boolean aux = deleteValue(root, key);
			if(aux) {
				weight--;
			}
			return aux;
		}
	}
	
	private boolean deleteValue(Node<K,E> root,K value) {
		if(root==null) {
			return false;
		}
		if(root.getKey().toString().compareTo(value.toString())<0) {
				boolean aux = deleteValue(root.getRight(), value);
				if(aux) {
					if(root.getRight()!=null && root.getRight().getHeight()<root.getHeight()-1) {
						root.setHeight(root.getHeight()-1);
					}
				}
				return aux;
				
		}else if(root.getKey().toString().compareTo(value.toString())>0) {
			boolean aux = deleteValue(root.getLeft(),value);
			if(aux) {
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
	
	private boolean deleteTreeOneSon(Node<K,E> root) {
		if(root.getLeft()!=null) {
			Node <K,E>aux=root.getLeft();
			Node <K,E> p=root.getParent();
			if(p.getRight()==root) {
				p.setRight(aux);
			}else {
				p.setLeft(aux);
			}
			aux.setParent(p);
			return true;
		}else if(root.getRight()!=null) {
			Node <K,E>aux=root.getRight();
			Node <K,E> p=root.getParent();
			if(p.getRight()==root) {
				p.setRight(aux);
			}else {
				p.setLeft(aux);
			}
			aux.setParent(p);
			return true;
		}
		return false;
	}
	private boolean deleteTreeTwoSons(Node<K,E> root) {
		Node<K,E> minRightValue = minimunValue(root.getRight());
		if(minRightValue.getRight()!=null) {
			Node<K,E> temp =minRightValue;
			deleteTreeOneSon(minRightValue);
			if(root==this.root) {
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
				this.root=temp;
				return true;
			}else {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				Node<K,E> pop = root.getParent();
				if(pop.getLeft()==root) {
					pop.setLeft(temp);
				}else if(pop.getRight()==root) {
					pop.setRight(temp);
				}
				if(root.getLeft()!=null ) {
					root.getLeft().setParent(temp);
				}
				if(root.getRight()!=null) {
					root.getRight().setParent(temp);
				}
				temp.setParent(pop);
				root=temp;
				int height1 = 1;
				int height2 = 1;
				if(temp.getLeft()!=null ) {
					height1 = temp.getLeft().getHeight();
				}
				if(temp.getRight()!=null ) {
					height2 = temp.getRight().getHeight();
				}
				root.setHeight(Math.max(height1, height2)+1);
				this.root=temp;
				return true;
			}
		}else {
			Node<K,E> temp =minRightValue;
			deleteTreeNoSons(minRightValue);
			if(root==this.root) {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				root.getLeft().setParent(temp);
				root.getRight().setParent(temp);
				this.root=temp;
				return true;
			}else {
				temp.setRight(root.getRight());
				temp.setLeft(root.getLeft());
				Node<K,E> pop = root.getParent();
				if(pop.getLeft()==root) {
					pop.setLeft(temp);
				}else if(pop.getRight()==root) {
					pop.setRight(temp);
				}
				root.getLeft().setParent(temp);
				root.getRight().setParent(temp);
				root=temp;
				return true;
			}
		}
	}
	
	private boolean deleteTreeNoSons(Node<K,E> node) {
		if(node == this.root) {
			node = null;
		}else {
			Node<K,E> parent = node.getParent();
			if(parent.getLeft()==node) {
				parent.setLeft(null);
			}else {
				parent.setRight(null);
			}
		}
		
		return true;
	}
	
	//Delete end
	
	@Override
	public int height() {
		if(root != null) {
			height(root);
		}
		return 0;
	}
	
	
	public int height(Node<K,E> currentNode) {
		
		if(currentNode!=null) {
			return currentNode.getHeight();
		}
		return 0;
	}
	

	@Override
	public int weight() {
		return this.getWeight();
	}
	//rch method
	@Override
	public E searchValue(K key) {
		if(root==null) {
			return null;
		}else {
			return searchValue(root,key);
		}
	}
	private E searchValue(Node<K,E> root,K key){
		if(root==null) {
			return null;
		}else if(root.getKey().toString().compareTo(key.toString())==0) {
			return root.getElement();
		}else if(root.getKey().toString().compareTo(key.toString())>0) {
			return searchValue(root.getLeft(),key);
		}else {
			return searchValue(root.getRight(),key);
		}
	}
	//Search method end
	@Override
	public ArrayList<E> inOrder() {
		ArrayList<E> list = new ArrayList<E>();
		if(root!=null) {
			inOrderR(root,list);
		}
		return list;
	}
	
	private void inOrderR(Node<K,E> current, ArrayList<E> list) {
		if(current.getLeft()==null) {
			list.add(current.getElement());
		}else {
			inOrderR(current.getLeft(),list);
		}
		if(current.getRight()!=null) {
			inOrderR(current.getRight(),list);
		}
	}

	@Override
	public ArrayList<E> postOrder() {
		ArrayList<E> list = new ArrayList<>();
		if (root != null) {
		 	postOrder(root, list);
		}
		 return (ArrayList<E>) list;
	}
	
	private void postOrder(Node<K,E> current, ArrayList<E> list) {
		
		if (current.getLeft() != null) {
			postOrder(current.getLeft(), list);
		}
		else if (current.getRight() != null) {
			postOrder(current.getRight(), list);
		}
		
		list.add(current.getElement());
	}

	@Override
	public ArrayList<E> preOrder(){
		ArrayList<E> list = new ArrayList<E>();
		if(root != null) {
			preOrder(list, root);
		}
		return list;
	}
	
	private void preOrder(ArrayList<E> list,Node<K,E> n) {
		
		list.add(n.getElement());
		
		if(n.getLeft() != null) {
			preOrder(list, n.getLeft());
		}
		if(n.getRight() != null) {
			preOrder(list, n.getRight());
		}
	}
	
	public ArrayList<E> sensitiveSearch(K key) {
		amountSeach = 0;
		ArrayList<E> array = new ArrayList<>();
		//Node<K,E> current = searchValueSensitive(this.root,key);
		Node<K,E> current = this.root;
		if(current!=null) {
			searching = true;
			
			if(current.getHeight()>10) {
				SearchingThread<K,E> searchingThread1 = new SearchingThread<K,E>(this,current.getLeft(), key, array);
				SearchingThread<K,E> searchingThread2 = new SearchingThread<K,E>(this,current.getRight(), key, array);
				searchingThread1.start();
				searchingThread2.start();
				
				RunningThread<K,E> rt = new RunningThread<>(this, searchingThread1,searchingThread2);
				rt.start();
				
			}else {
				
				searchValuesSensitiveR(array,current,key);
				stopSearch();
			}
		}
		return array;
	}
	
	/*private Node<K,E> searchValueSensitive(Node<K,E> root,K key){
		if(root!=null && searching) {
			int aux = root.startsWith(key);
			if(aux==0) {
				return root;
			}else if(aux<0) {
				return searchValueSensitive(root.getLeft(),key);
			}else if(aux>0) {
				return searchValueSensitive(root.getRight(),key);
			}
		}
		return null;
	}*/
	
	public ArrayList<E> searchValuesSensitiveR(ArrayList<E> array,Node<K,E> root,K key){
		if(root!=null && searching ) {
			int aux = root.startsWith(key);
			if(aux==0 ) {
				amountSeach++;
				if( amountSeach<=20) {
					array.add(root.getElement());
				}else {
					//array.clear();
				}
			}
			searchValuesSensitiveR(array,root.getLeft(),key);
			searchValuesSensitiveR(array,root.getRight(),key);
			
		}
		return array;
	}
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void stopSearch() {
		searching = false;
	}
	
}
