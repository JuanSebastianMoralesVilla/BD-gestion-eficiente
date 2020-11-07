package threads;

import java.io.FileNotFoundException;

import model.DataBase;

public class CreatingDataThread extends Thread{
	private DataBase dataBase;
	private int ammount;
	public CreatingDataThread(DataBase dataBase,int ammount) {
		this.dataBase = dataBase;
		this.ammount = ammount;
	}
	
	@Override
	public void run() {
		try {
			dataBase.generateUsers(ammount);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
