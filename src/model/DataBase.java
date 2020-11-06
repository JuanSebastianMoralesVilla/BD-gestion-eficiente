package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import custom_exceptions.InvalidValueException;
import custom_exceptions.ValuesIsEmptyException;
import data_structures.AVLTree;
import threads.CreatingUserThread;

public class DataBase {
	private AVLTree<String, User> usersByID;
	private AVLTree<String, User> usersByName;
	private AVLTree<String, User> usersByLastName;
	private AVLTree<String, User> usersByFullName;
	
	private double seachingAvance;
	private ArrayList<String> names_gender;
	private ArrayList<String> lastNames;
	private String[] countries;
	private String[] ages_proportions;
	private int[] currentIDs;
	public final static int AMMOUNT_COUNTRIES =34;
	
	public final static String ID = "id";
	public final static String NAME = "name";
	public final static String LAST_NAME = "lastName";
	public final static String FULL_NAME = "fullName";
	public DataBase() throws IOException {
		usersByID = new AVLTree<>();
		usersByName = new AVLTree<>();
		usersByLastName = new AVLTree<>();
		usersByFullName = new AVLTree<>();
		currentIDs = new int[34];
		seachingAvance = 0;
		File names = new File("data/names_gender.csv");
		File lastNames = new File("data/lastName");
		File countries = new File("data/population_by_country.csv");
		File ages = new File("data/ages_proportion.csv");
		BufferedReader br = new BufferedReader(new FileReader(names));
		names_gender = new ArrayList<>();
		String str = br.readLine();
		while(str != null) {
			names_gender.add(str);
			str = br.readLine();
		}
		br = new BufferedReader(new FileReader(lastNames));
		this.lastNames = new ArrayList<>();
		str = br.readLine();
		while(str != null) {
			this.lastNames.add(str);
			str = br.readLine();
		}
		br = new BufferedReader(new FileReader(countries));
		this.countries = new String[AMMOUNT_COUNTRIES];
		str = br.readLine();
		int i =0;
		while(str != null) {
			this.countries[i] = str;
			str = br.readLine();
			i++;
		}
		br = new BufferedReader(new FileReader(countries));
		this.ages_proportions = new String[5];
		str = br.readLine();
		i =0;
		while(str != null) {
			this.ages_proportions[i] = str;
			str = br.readLine();
			i++;
		}
		
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
	//String name, String lastName,String id, String gender,double stature, String nationality, LocalDate dayOfBHD, String picture
	public void generateUsers(int ammount) throws FileNotFoundException {
		
		
		CreatingUserThread<String, User> userById;
		CreatingUserThread<String, User> userByName;
		CreatingUserThread<String, User> userByLastName;
		CreatingUserThread<String, User> userByFullName;
		for (int i = 0; i < ammount; i++) {
			String[] fullName = generateFullNames();
			String name = fullName[0];
			String lastName = fullName[1];
			String nationality = generateNationality();
			String gender = fullName[2];
			String id = generateID(nationality);
			double stature = generateStature();
			LocalDate dayOfBHD = generateAge();
			String picture = generateImage();
			User user = new User(name,lastName,id,gender,stature,nationality,dayOfBHD,picture);
			userById = new CreatingUserThread<String, User>(this.usersByID, id, user);
			userByName = new CreatingUserThread<String, User>(this.usersByName, id, user);
			userByLastName = new CreatingUserThread<String, User>(this.usersByLastName, id, user);
			userByFullName = new CreatingUserThread<String, User>(this.usersByFullName, id, user);
			userById.start();
			userByName.start();
			userByLastName.start();
			userByFullName.start();
			while(userById.isAlive() || userByName.isAlive() ||  userByLastName.isAlive() ||  userByFullName.isAlive()) {
				System.out.println("Waiting...");
			}
			seachingAvance = Double.parseDouble(i+"")/Double.parseDouble(ammount+"");
		}
	}
	
	//Order is Name>LastName>Gender
	private String[] generateFullNames() throws FileNotFoundException {
		Random random = new Random(System.currentTimeMillis());
		
		return null;
	}
	
	private LocalDate generateAge() {
		Random random = new Random(System.currentTimeMillis());
		double country = random.nextDouble()*100;
		
		int ageMin =0;
		int ageMax = 0;
		for (int i = 0; i < ages_proportions.length; i++) {
			ageMax  = Integer.parseInt(ages_proportions[i].split(";")[0]);
			double probability = Double.parseDouble( countries[i].split(";")[1]);
			if(country<=probability) {
				break;
			}
			ageMin = ageMax;
		}
		int age = random.nextInt(ageMax-ageMin+1)+ageMin;
		LocalDate date = LocalDate.now();
		date.minusYears(age);
		date.minusDays(random.nextInt(363));
		return date;
	}
	
	private double generateStature() {
		return 0;
	}
	
	private String generateNationality() {
		Random random = new Random(System.currentTimeMillis());
		double country = random.nextDouble()*100;
		String currentCountry = "";
		for (int i = 0; i < countries.length; i++) {
			currentCountry  = countries[i].split(";")[0];
			double probability = Double.parseDouble( countries[i].split(";")[1]);
			if(country<=probability) {
				break;
			}
		}
		return currentCountry;
	}
	
	private String generateID(String nationality) {
		return null;
	}
	
	private String generateImage() {
		return null;
	}
	
	
	public void saveData() {
		
	}
	public double getSeachingAvance() {
		return seachingAvance;
	}
	
}
