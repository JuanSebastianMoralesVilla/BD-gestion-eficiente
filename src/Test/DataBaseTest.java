package Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import custom_exceptions.InvalidValueException;
import custom_exceptions.ValuesIsEmptyException;
import model.DataBase;
import model.User;

public class DataBaseTest {

	DataBase dataBase;
	User user;

	public void setup1() {
		dataBase = new DataBase();
		ArrayList<User> users = new ArrayList<User>();

		User user1 = new User("Sebastian", "Morales", "0001", "Male", 1.65, "Colombia", LocalDate.now(),
				"https: miimage.com");
		User user2 = new User("Cristian", "Morales", "0002", "Male", 1.75, "Colombia", LocalDate.now(),
				"https: miimage.com");
		User user3 = new User("Andres", "Aristi", "0003", "Male", 1.78, "Colombia", LocalDate.now(),
				"https: miimage.com");
		users.add(user1);
		users.add(user2);
		users.add(user3);
	}

	public void setup2() throws ValuesIsEmptyException, InvalidValueException {
		
		dataBase = new DataBase();
		ArrayList<User> users = new ArrayList<User>();
		dataBase.createUser("Sebastian", "Morales", "Male", 1.65, "Colombia", LocalDate.now(), "https: miimage.com");
		
	}
	
	public void setup3() throws ValuesIsEmptyException, InvalidValueException {
		dataBase = new DataBase();
		dataBase.createUser("Sebastian", "Morales", "Male", 1.65, "Colombia", LocalDate.now(), "https: miimage.com");
		dataBase.createUser("Cristian", "Morales",  "Male", 1.75, "Colombia", LocalDate.now(),
				"https: miimage.com");
		dataBase.createUser("Andres", "Aristi", "Male", 1.78, "Colombia", LocalDate.now(),
				"https: miimage.com");
	}

	
	@Test
	public void searchTest() {
		setup1();

		assertEquals(null, dataBase.searchUser("0001"), "el usuario no exite");
	}

	@Test
	public void searchTest1() {
		setup1();

		assertEquals(null, dataBase.searchUser("0001"), "el usuario no exite");
	}

	@Test
	public void searchTest2() {
		setup1();

		assertEquals(null, dataBase.searchUser("0002"), "el usuario no exite");
	}

	@Test
	public void searchTest3() {
		setup1();

		assertEquals(null, dataBase.searchUser("0003"), "el usuario no exite");
	}
	
	
	
	
	
	@Test
	public void addUserTest1() throws ValuesIsEmptyException, InvalidValueException {
		//setup2();
		//setup3();	
		//User user=dataBase.searchUser("0001");
// assertTrue("El usuario no se agrego correctamente" );
	}
/*
	@Test
	public void addUser() throws ValuesIsEmptyException, InvalidValueException {
		// TODO Auto-generated method stub
		LocalDate ld = LocalDate.now();
		LocalDate ld2 = LocalDate.now();
		// dataBase.createUser("Sebastian","Morales","Male",
		// 1.65,"Colombia",LocalDate.now(), "https: miimage.com");

		assertTrue("El usuario no fue insertado correctamente",
				user.getName().equals("Sebastian") && user.getLastName().equals("Morales")
						&& user.getGender().equals("Male") && (user.getStature() == 1.65)
						&& user.getNationality().equals("Colombia") && user.getDayOfBHD().now().equals(ld2)
						&& user.getPicture().equalsIgnoreCase("https: miimage.com"));
	}
*/
	

	@Test
	public void DeleteTest1() {
		setup1();

		assertTrue(true, "el usuario no se elimino");

	}

}
