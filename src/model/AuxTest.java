package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import custom_exceptions.InvalidValueException;
import custom_exceptions.ValuesIsEmptyException;

public class AuxTest {
	public static void main(String[] args) throws IOException, ValuesIsEmptyException, InvalidValueException {
		double start = System.currentTimeMillis();
		DataBase data = new DataBase();
		data.generateUsers(100);
		data.saveData();
		//System.out.println(data.sensitiveSearch("C", data.NAME));
	}
	
}
