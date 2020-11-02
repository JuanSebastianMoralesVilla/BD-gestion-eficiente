package model;

import java.time.LocalDate;

import custom_exceptions.InvalidValueException;
import custom_exceptions.ValuesIsEmptyException;
import data_structures.AVLTree;

public class DataBase {
	private AVLTree<String, User> usersByID;
	private AVLTree<String, User> usersByName;
	private AVLTree<String, User> usersByLastName;
	private AVLTree<String, User> usersByFullName;
	
	private int[] currentIDs;
	public final static int AMMOUNT_COUNTRIES =35;
	
	public final static String ID = "id";
	public final static String NAME = "name";
	public final static String LAST_NAME = "lastName";
	public final static String FULL_NAME = "fullName";
	public DataBase() {
		usersByID = new AVLTree<>();
		usersByName = new AVLTree<>();
		usersByLastName = new AVLTree<>();
		usersByFullName = new AVLTree<>();
		currentIDs = new int[110];
	}
	/**
	 * 
	 * @param name this parameter is a String that represent the name of the user to create
	 * @param lastName this parameter is a String that represent the last_name of the user to create
	 * @param gender this parameter is a String that represent the gender, and this only can be "Male" or "Female"
	 * @param stature this is the stature that have the user, is a positive real number
	 * @param nationality this is a String that represent the Nationality of the user, this only can be Countries in LATAM
	 * @param dayOfBHD This parameter represent the birthday of the user
	 * @param picture
	 * @throws ValuesIsEmptyException 
	 * @throws InvalidValueException 
	 * 
	 */
	public void createUser(String name, String lastName, String gender,double stature, String nationality, LocalDate dayOfBHD, String picture) throws ValuesIsEmptyException, InvalidValueException {
		
		if(name.isEmpty()||lastName.isEmpty()||gender.isEmpty()||nationality.isEmpty()) {
			throw new ValuesIsEmptyException();
		}
		if(stature<User.MIN_STATURE || stature>User.MAX_STATURE) {
			throw new InvalidValueException();
		}
		if(dayOfBHD.compareTo(LocalDate.now())>0) {
			throw new InvalidValueException();
		}
		String id = generateID(nationality);
		User newUser = new User(name,lastName,id,gender,stature,nationality,dayOfBHD,picture);
		usersByID.insert(id, newUser);
		usersByName.insert(name, newUser);
		usersByLastName.insert(lastName,newUser);
		usersByFullName.insert(name+" "+lastName,newUser);
	}
	/**
	 * 
	 * @param type
	 * @param key
	 * @return
	 */
	public User searchUser(String key) {
		return usersByID.searchValue(key);
		
	}
	//Si la tableView retorna el objeto guardado se deja user, de lo contrario se cambia a "String key" con el codigo del usuario
	public boolean delateUser(User user) {
		return false;
	}
	
	//En la GUI al editar llenar las barras con las obciones actuales
	public  void updateUser(User user,String name, String lastName, String gender, double stature, String nationality, LocalDate dayOfBHD, String picture) throws ValuesIsEmptyException, InvalidValueException {
		if(name.isEmpty()||lastName.isEmpty()||gender.isEmpty()||nationality.isEmpty()) {
			throw new ValuesIsEmptyException();
		}
		if(stature<User.MIN_STATURE || stature>User.MAX_STATURE) {
			throw new InvalidValueException();
		}
		if(dayOfBHD.compareTo(LocalDate.now())>0) {
			throw new InvalidValueException();
		}
		user.setName(name);
		user.setLastName(name);
		user.setGender(gender);
		user.setStature(stature);
		user.setNationality(nationality);
		user.setDayOfBHD(dayOfBHD);
		user.setPicture(picture);
	}
	
	public void generateUsers(int ammount) {
		
	}
	
	private String[] generateFullNames() {
		return null;
	}
	
	private LocalDate generateAge() {
		return null;
	}
	
	private double generateStature() {
		return 0;
	}
	
	private String generateNationality() {
		return null;
	}
	
	private String generateID(String nationality) {
		return null;
	}
	
	private String generateImage() {
		return null;
	}
	
	private String generateGender() {
		return null;
	}
	
	public void saveData() {
		
	}
	
}
