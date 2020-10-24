package model;

import java.time.LocalDate;

public class User {
	
	private String name;
	private String lastName;
	private boolean gender;
	private boolean male;
	private boolean female;
	private double stature;
	private String nationality;
	private LocalDate dayOfBHD;
	private String picture;
	
	
	
	public User(String name, String lastName, boolean gender, boolean male, boolean female, double stature,
			String nationality, LocalDate dayOfBHD, String picture) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.male = male;
		this.female = female;
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
	
	
	

}
 