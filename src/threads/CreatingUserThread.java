package threads;

import data_structures.AVLTree;
import model.DataBase;
import model.User;

public class CreatingUserThread extends Thread{
	private AVLTree<String,User> avlTree;
	private User[] array;
	private String type;
	private DataBase data;
	public CreatingUserThread(DataBase dataBase,AVLTree<String,User> avlTree,User[] array,String type) {
		this.avlTree = avlTree;
		this.array = array;
		this.type = type;
		data = dataBase;
	}
	@Override
	public void run() {
		switch(type.toString()) {
		case DataBase.ID:
			for (int i = 0; i < array.length; i++) {
				User user = array[i];
				avlTree.insert(user.getId(), user);
			}
			break;
		case DataBase.NAME:
			for (int i = 0; i < array.length; i++) {
				User user = array[i];
				avlTree.insert(user.getName(), user);
				data.advance();
			}
			break;
		case DataBase.LAST_NAME:
			for (int i = 0; i < array.length; i++) {
				User user = array[i];
				avlTree.insert(user.getLastName(), user);
				data.advance();
			}
			break;
		case DataBase.FULL_NAME:
			for (int i = 0; i < array.length; i++) {
				User user = array[i];
				avlTree.insert(user.getName()+ " "+user.getLastName(), user);
				data.advance();
			}
			break;
		}
		
		
	}
}
