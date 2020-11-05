package model;

import java.time.LocalDate;

public class User {
	
	private String name;
	private String lastName;
	private String gender;
	private String id;
	private double stature;
	private String nationality;
	private LocalDate dayOfBHD;
	private String picture;
	public final static String MALE = "male";
	public final static String FEMALE = "female";
	public final static double MIN_STATURE = 50;
	public final static double MAX_STATURE = 300;
	
	public User(String name, String lastName,String id, String gender,double stature, String nationality, LocalDate dayOfBHD, String picture) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.stature = stature;
		this.nationality = nationality;
		this.dayOfBHD = dayOfBHD;
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getStature() {
		return stature;
	}

	public void setStature(double stature) {
		this.stature = stature;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public LocalDate getDayOfBHD() {
		return dayOfBHD;
	}

	public void setDayOfBHD(LocalDate dayOfBHD) {
		this.dayOfBHD = dayOfBHD;
	}

	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getId() {
		return id;
	}
}
 