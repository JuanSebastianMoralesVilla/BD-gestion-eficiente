package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import custom_exceptions.InvalidValueException;
import custom_exceptions.ValuesIsEmptyException;
import data_structures.AVLTree;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import threads.CreatingUserThread;

public class DataBase {
	private AVLTree<String, User> usersByID;
	private AVLTree<String, User> usersByName;
	private AVLTree<String, User> usersByLastName;
	private AVLTree<String, User> usersByFullName;
	
	private ArrayList<String> names_gender;
	private ArrayList<String> lastNames;
	private String[] countries;
	private String[] ages_proportions;
	private int currentIDs;
	public final static int AMMOUNT_COUNTRIES =34;
	
	private boolean process1;
	private boolean process2;
	private boolean process3;
	private boolean process4;
	
	private boolean creating;
	private double currentAmmount;
	private double totalAmmount;
	private double loadingAdvance;
	ArrayList<User> users;
	
	public final static String ID = "id";
	public final static String NAME = "name";
	public final static String LAST_NAME = "lastName";
	public final static String FULL_NAME = "fullName";
	public final static String FILE = ".data";
	public DataBase() throws IOException {
		users = new ArrayList<User>();
		usersByID = new AVLTree<>();
		usersByName = new AVLTree<>();
		usersByLastName = new AVLTree<>();
		usersByFullName = new AVLTree<>();
		currentIDs = 00000;
		creating = false;
		File names = new File("data/names_gender.csv");
		File lastNames = new File("data/lastNames.csv");
		File countries = new File("data/population_by_country.csv");
		File ages = new File("data/ages_proporcion.csv");
		
		BufferedReader br = new BufferedReader(new FileReader(names));
		names_gender = new ArrayList<>();
		String str = br.readLine();
		while(str != null) {
			names_gender.add(str);
			str = br.readLine();
		}
		br.close();
		br = new BufferedReader(new FileReader(lastNames));
		this.lastNames = new ArrayList<>();
		str = br.readLine();
		while(str != null) {
			this.lastNames.add(str);
			str = br.readLine();
		}
		br.close();
		br = new BufferedReader(new FileReader(countries));
		this.countries = new String[AMMOUNT_COUNTRIES];
		str = br.readLine();
		int i =0;
		while(str != null) {
			this.countries[i] = str;
			str = br.readLine();
			i++;
		}
		br.close();
		br = new BufferedReader(new FileReader(ages));
		this.ages_proportions = new String[5];
		str = br.readLine();
		i =0;
		while(str != null) {
			this.ages_proportions[i] = str;
			str = br.readLine();
			i++;
		}
		br.close();
		
	}
	public AVLTree<String, User> getUsersByID() {
		return usersByID;
	}
	public void setUsersByID(AVLTree<String, User> usersByID) {
		this.usersByID = usersByID;
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
	 * @throws Exception 
	 * 
	 */
	public User createUser(String name, String lastName, String gender,double stature, String nationality, LocalDate dayOfBHD) throws ValuesIsEmptyException, InvalidValueException, Exception {
		
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
			URL url = new URL("https://thispersondoesnotexist.com/image");
			BufferedImage c = ImageIO.read(url);
			Image picture = SwingFXUtils.toFXImage(c, null);
//			ImageIcon picture = new ImageIcon(c);
			User newUser = new User(name,lastName,id,gender,stature,nationality,dayOfBHD,picture);
			usersByID.insert(id, newUser);
			usersByName.insert(name, newUser);
			usersByLastName.insert(lastName,newUser);
			usersByFullName.insert(name+" "+lastName,newUser);
			return newUser;
			
	

	}
	/**
	 * @param type
	 * @param key
	 * @return
	 */
	public User searchUser(String key) {
		
		return usersByID.searchValue(key);
		
	}
	public ArrayList<User> sensitiveSearch(String key, String parameter) {
		switch(parameter) {
		case ID:
			usersByID.stopSearch();
			return usersByID.sensitiveSearch(key);
			
		case NAME:
			usersByName.stopSearch();
			return usersByName.sensitiveSearch(key);
			
		case LAST_NAME:
			usersByLastName.stopSearch();
			return usersByLastName.sensitiveSearch(key);
			
		case FULL_NAME:
			usersByLastName.stopSearch();
			return usersByLastName.sensitiveSearch(key);
			
		}
		return null;
	}
	
	
	public Image generatePicture() {
		
		
	
		try {
			URL url = new URL("https://thispersondoesnotexist.com/image");
			BufferedImage c = ImageIO.read(url);
			Image picture = SwingFXUtils.toFXImage(c, null);
			return picture;
		} catch (Exception e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	}
	//Si la tableView retorna el objeto guardado se deja user, de lo contrario se cambia a "String key" con el codigo del usuario
	public boolean delateUser(User user) {
		String id = user.getId();
		String name = user.getName();
		String lastName = user.getLastName();
		String fullName = name + " " + lastName;
		boolean result = usersByID.deleteValue(id);
		usersByName.deleteValue(name);
		usersByLastName.deleteValue(lastName);
		usersByFullName.deleteValue(fullName);
		return result;
	}
	
	//En la GUI al editar llenar las barras con las obciones actuales
	public  void updateUser(User user,String name, String lastName, String gender, double stature, String nationality, LocalDate dayOfBHD, Image picture) throws ValuesIsEmptyException, InvalidValueException {
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
		totalAmmount = ammount*4;
		users = new ArrayList<User>();
		process1 = true;
		creating = true;
		for (int i = 0; i < ammount; i++) {
			
			String[] fullName = generateFullNames();
			String name = fullName[0];
			String lastName = fullName[1];
			String nationality = generateNationality();
			String gender = fullName[2];
			String id = generateID(nationality);
			double stature = generateStature();
			LocalDate dayOfBHD = generateAge();
			Image picture = null;
			User user = new User(name,lastName,id,gender,stature,nationality,dayOfBHD,picture);
			users.add(user);
			double n = i;
			double d = ammount;
			loadingAdvance =n/d;
		}
		process1 = false;
		creating = false;
	}
	
	//Order is Name>LastName>Gender
	private String[] generateFullNames() throws FileNotFoundException {
		Random random = new Random();
		String[] fullName = new String[3];
		int aux = random.nextInt(names_gender.size()-1);
		String[] names = names_gender.get(aux).split(",");
		fullName[0] = names[0];
		fullName[2] = names[1];
		aux = random.nextInt(lastNames.size()-1);
		fullName[1] = lastNames.get(aux);
		return fullName;
	}
	
	private LocalDate generateAge() {
		Random random = new Random();
		double firstProba = random.nextDouble()*100;
		
		int ageMin =0;
		int ageMax = 0;
		for (int i = 0; i < ages_proportions.length; i++) {
			ageMax  = Integer.parseInt(ages_proportions[i].split(";")[0]);
			double probability = Double.parseDouble( ages_proportions[i].split(";")[1]);
			if(firstProba<=probability) {
				break;
			}
			ageMin = ageMax;
		}
		int age = random.nextInt(ageMax-ageMin+1)+ageMin;
		
		LocalDate date = LocalDate.now();
		date = date.minusYears(age);
		date = date.minusDays(random.nextInt(363));
		return date;
	}
	
	private double generateStature() {
		Random random = new Random();
		double stature = random.nextDouble()*200+50;
		
		return stature;
	}
	
	private String generateNationality() {
		Random random = new Random();
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
	
	public String generateID(String nationality) {
		String code = Math.abs(nationality.hashCode())+""+currentIDs;
		currentIDs++;
		return code;
	}
	
	private String generateImage() {
		return null;
	}
	
	public void saveInAVL() {
		creating = true;
		CreatingUserThread threadByID = new CreatingUserThread(this,usersByID, users, ID);
		CreatingUserThread threadByNAME = new CreatingUserThread(this,usersByName, users, NAME);
		CreatingUserThread threadByLAST_NAME = new CreatingUserThread(this,usersByLastName, users,LAST_NAME);
		CreatingUserThread threadByFULL_NAME = new CreatingUserThread(this,usersByFullName, users, FULL_NAME );
		
		threadByID.start();
		threadByNAME.start();
		threadByLAST_NAME.start();
		threadByFULL_NAME.start();
		
	}
	
	public void saveData() throws IOException {
		
		 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/"+ID+FILE) );
		 oos.writeObject(usersByID);
		 oos.close();
		 oos = new ObjectOutputStream(new FileOutputStream("data/"+NAME+FILE) );
		 oos.writeObject(usersByName);
		 oos.close();
		 oos = new ObjectOutputStream(new FileOutputStream("data/"+LAST_NAME+FILE) );
		 oos.writeObject(usersByLastName);
		 oos.close();
		 oos = new ObjectOutputStream(new FileOutputStream("data/"+FULL_NAME+FILE) );
		 oos.writeObject(usersByFullName);
		 oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadData() throws IOException {
		 ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/"+ID+FILE) );
		 try {
			 usersByID  = (AVLTree<String,User>)ois.readObject();
			 ois.close();
			 ois = new ObjectInputStream(new FileInputStream("data/"+NAME+FILE));
			 usersByName =  (AVLTree<String,User>)ois.readObject();
			 ois.close();
			 ois = new ObjectInputStream(new FileInputStream("data/"+LAST_NAME+FILE));
			 usersByLastName =  (AVLTree<String,User>)ois.readObject();
			 ois.close();
			 ois = new ObjectInputStream(new FileInputStream("data/"+FULL_NAME+FILE));
			 usersByFullName =  (AVLTree<String,User>)ois.readObject();
		}catch (ClassNotFoundException | IOException e) {
			System.out.println("No encontrado");
		}
		 ois.close();
		
	}
	
	public void advance() {
		currentAmmount++;
		loadingAdvance = currentAmmount/totalAmmount;
		if(loadingAdvance==1) {
			creating = false;
		}
		
	}
	public double getSeachingAvance() {
		if(!process1 && !process2 && !process3 && !process4) {
			creating = false;
		}
		return loadingAdvance;
	}
	public boolean isCreating() {
		return creating;
	}
	public void setCreating(boolean creating) {
		this.creating = creating;
	}
	public boolean isProcess1() {
		return process1;
	}
	public void setProcess1(boolean process1) {
		this.process1 = process1;
	}
	public boolean isProcess2() {
		return process2;
	}
	public void setProcess2(boolean process2) {
		this.process2 = process2;
	}
	public boolean isProcess3() {
		return process3;
	}
	public void setProcess3(boolean process3) {
		this.process3 = process3;
	}
	public boolean isProcess4() {
		return process4;
	}
	public void setProcess4(boolean process4) {
		this.process4 = process4;
	}
	
}
