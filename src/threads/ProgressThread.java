package threads;

import model.DataBase;
import ui.DataBaseGUI;

public class ProgressThread extends Thread{
	private DataBase dataBase;
	private DataBaseGUI dataBaseGUI;
	
	public ProgressThread(DataBase dataBase,DataBaseGUI dataBaseGUI) {
		this.dataBase = dataBase;
		this.dataBaseGUI = dataBaseGUI;
	}
	@Override
	public void run() {
		dataBaseGUI.setProgressBar();
	}
}
