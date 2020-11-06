package Test;

import java.time.LocalDate;
import java.util.ArrayList;

import model.DataBase;
import model.User;

public class DataBaseTest {

	DataBase dataBase;
	User user;
	
	public void setup1(){
 ArrayList<User> users= new ArrayList<User>();
 
 User user1=  new User("Sebastian","Morales","0001", "Male",1.65,"Colombia",LocalDate.now(), "https: miimage.com" );
 User user2=  new User("Sebastian","Morales","0001", "Male",1.65,"Colombia",LocalDate.now(), "https: miimage.com" );
 User user3=  new User("Sebastian","Morales","0001", "Male",1.65,"Colombia",LocalDate.now(), "https: miimage.com" );
	users.add(user1);
	
	
	}
	
}
