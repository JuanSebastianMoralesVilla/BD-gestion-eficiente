package threads;

import java.util.ArrayList;

import data_structures.BinarySearchTree;
import data_structures.Node;

public class SearchingThread<K extends Comparable<K>,E> extends Thread{
	private BinarySearchTree<K,E> avlTree;
	private Node<K,E> node;
	private K key;
	private ArrayList<E> array;
	public SearchingThread(BinarySearchTree<K,E> avlTree,Node<K,E> node,K key, ArrayList<E> array) {
		this.avlTree = avlTree;
		this.node = node;
		this.key = key;
		this.array = array;
	}
	@Override
	public void run() {
		avlTree.searchValuesSensitiveR(array, node, key);
	}
}
