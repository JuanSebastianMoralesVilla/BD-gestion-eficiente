package threads;

import java.util.ArrayList;

import model.DataBase;
import model.User;
import ui.DataBaseGUI;

public class DataSearchingThread extends Thread{
	private DataBase dataBase;
	private ArrayList<User> users;
	private String key;
	private String parameter;
	private DataBaseGUI databaseGUI;
	public DataSearchingThread(DataBase dataBase,DataBaseGUI databaseGUI,String key,String parameter) {
		this.dataBase = dataBase;
		this.key = key;
		this.parameter = parameter;
		this.databaseGUI = databaseGUI;
	}
	@Override
	public void run() {
		users = dataBase.sensitiveSearch(key, parameter);
		databaseGUI.loadTable(users);
	}
}
