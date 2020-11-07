package model;

import java.io.IOException;
import java.util.ArrayList;

public class AuxTest {
	public static void main(String[] args) throws IOException {
		double start = System.currentTimeMillis();
		DataBase data = new DataBase();
		data.generateUsers(100000);
		ArrayList<User> array = data.sensitiveSearch("A", data.NAME);
		System.out.println(array.size());
		for (int i = 0; i < args.length; i++) {
			System.out.println(array.get(i).toString());
		}
		//double finish = System.currentTimeMillis();
		//System.out.println((finish-start)/1000);
		//System.out.println(data.getUsersByID().preOrder().size());
	}
}
