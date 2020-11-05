package threads;

import data_structures.AVLTree;

public class CreatingUserThread <K extends Comparable<K>,E> extends Thread{
	private AVLTree<K,E> avlTree;
	private E value;
	private K key;
	public CreatingUserThread(AVLTree<K,E> avlTree,K key,E value) {
		this.avlTree = avlTree;
		this.value = value;
		this.key = key;
	}
	@Override
	public void run() {
		avlTree.insert(key, value);
	}
}
