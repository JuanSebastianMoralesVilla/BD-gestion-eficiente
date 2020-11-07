package threads;

import java.util.ArrayList;

import data_structures.AVLTree;
import model.DataBase;
import model.User;

public class CreatingUserThread extends Thread{
	private AVLTree<String,User> avlTree;
	private ArrayList<User> array;
	private String type;
	private DataBase data;
	public CreatingUserThread(DataBase dataBase,AVLTree<String,User> avlTree,ArrayList<User> array,String type) {
		this.avlTree = avlTree;
		this.array = array;
		this.type = type;
		data = dataBase;
	}
	@Override
	public void run() {
		switch(type.toString()) {
		case DataBase.ID:
			data.setProcess1(true);
			for (int i = 0; i < array.size(); i++) {
				User user = array.get(i);
				avlTree.insert(user.getId(), user);
				data.advance();
			}
			data.setProcess1(false);
			break;
		case DataBase.NAME:
			data.setProcess2(true);
			for (int i = 0; i < array.size(); i++) {
				User user = array.get(i);
				avlTree.insert(user.getName(), user);
				data.advance();
			}
			data.setProcess2(false);
			break;
		case DataBase.LAST_NAME:
			data.setProcess3(true);
			for (int i = 0; i < array.size(); i++) {
				User user = array.get(i);
				avlTree.insert(user.getLastName(), user);
				data.advance();
			}
			data.setProcess3(false);
			break;
		case DataBase.FULL_NAME:
			data.setProcess4(true);
			for (int i = 0; i < array.size(); i++) {
				User user = array.get(i);
				avlTree.insert(user.getName()+" "+user.getLastName(), user);
				data.advance();
			}
			data.setProcess4(false);
			break;
		}
		
		
	}
}
