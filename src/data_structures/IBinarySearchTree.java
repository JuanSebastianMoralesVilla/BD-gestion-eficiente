package data_structures;

import java.util.ArrayList;

public interface IBinarySearchTree<K extends Comparable<K>,E> {
	
	public void insert( K key,E value);
	public boolean deleteValue(K key);
	public int height();
	public int weight();
	public E searchValue(K key);
	public ArrayList<E> inOrder();
	public ArrayList<E> postOrder();
	public ArrayList<E> preOrder();

}
