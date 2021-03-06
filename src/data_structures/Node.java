package data_structures;

import java.io.Serializable;

public class Node<K extends Comparable<K>, E> implements Serializable{

	private E element;
	private K key;
	private int height;
	private Node<K,E> parent;
	private Node<K,E> left;
	private Node<K,E> right;
	
	public Node(K key, E element) {
		height =1;
		this.element = element;
		this.key = key;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public Node<K, E> getParent() {
		return parent;
	}

	public void setParent(Node<K, E> parent) {
		this.parent = parent;
	}

	public Node<K, E> getLeft() {
		return left;
	}

	public void setLeft(Node<K, E> left) {
		this.left = left;
	}

	public Node<K, E> getRight() {
		return right;
	}

	public void setRight(Node<K, E> right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int startsWith(K prefix) {
		int result = 0;
		String currentKey = key.toString();
		String prefixR = prefix.toString();
		if(!currentKey.startsWith(prefixR)) {
			if(prefixR.compareTo(currentKey)<0) {
				result = -1;
			}else {
				result = 1;
			}
		}
		return result;
		
	}
}