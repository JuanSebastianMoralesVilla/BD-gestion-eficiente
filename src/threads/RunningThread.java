package threads;

import data_structures.BinarySearchTree;

public class RunningThread<K extends Comparable<K>,E> extends Thread{
	private BinarySearchTree<K,E> binarySearch;
	private SearchingThread<K,E> searching1;
	private SearchingThread<K,E> searching2;
	public RunningThread(BinarySearchTree<K,E> binarySearch,SearchingThread<K,E> searching1,SearchingThread<K,E> searching2) {
		this.searching1 = searching1;
		this.searching2 = searching2;
		this.binarySearch = binarySearch;
	}
	
	@Override
	public void run() {
		while(searching1.isAlive() || searching2.isAlive()) {
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		binarySearch.stopSearch();
	}
	
}
