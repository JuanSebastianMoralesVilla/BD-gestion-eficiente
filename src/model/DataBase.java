package model;

import java.time.LocalDate;

import data_structures.AVLTree;

public class DataBase {
	private AVLTree<String, User> usersByID;
	private AVLTree<String, User> usersByName;
	private AVLTree<String, User> usersByLastName;
	private AVLTree<String, User> usersByFullName;
	
	public DataBase() {
		usersByID = new AVLTree<>();
		usersByName = new AVLTree<>();
		usersByLastName = new AVLTree<>();
		usersByFullName = new AVLTree<>();
	}
	public void createUser(String name, String lastName, String gender,double stature, String nationality, LocalDate dayOfBHD, String picture) {
		
	}
	
}
