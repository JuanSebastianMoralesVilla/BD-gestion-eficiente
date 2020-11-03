package threads;

import java.util.ArrayList;

import data_structures.BinarySearchTree;

public class RunningThread<K extends Comparable<K>,E> extends Thread{
	private ArrayList<E> array;
	private BinarySearchTree<K,E> binarySearch;
	public RunningThread(BinarySearchTree<K,E> binarySearch,ArrayList<E> array) {
		this.array = array;
		this.binarySearch = binarySearch;
	}
	
	@Override
	public void run() {
		while(binarySearch.isSearching()) {
			System.out.println(array);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
