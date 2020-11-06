package threads;

import java.util.ArrayList;

import data_structures.BinarySearchTree;
import data_structures.Node;

public class SearchingThread<K extends Comparable<K>,E> extends Thread{
	private BinarySearchTree<K,E> avlTree;
	private Node<K,E> node;
	private K key;
	private ArrayList<E> array;
	private Integer ammount;
	
	public SearchingThread(BinarySearchTree<K,E> avlTree,Node<K,E> node,K key, ArrayList<E> array,Integer ammount) {
		this.avlTree = avlTree;
		this.node = node;
		this.key = key;
		this.array = array;
		this.ammount = ammount;
	}
	@Override
	public void run() {
		
		avlTree.searchValuesSensitiveR(ammount,array, node, key);
	}
}
