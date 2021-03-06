package model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.swing.ImageIcon;

import javafx.scene.image.Image;

public class User implements Serializable{
	
	@Override
	public String toString() {
		return "User [name=" + name + ", lastName=" + lastName + ", gender=" + gender + ", id=" + id + ", stature="
				+ stature + ", nationality=" + nationality + ", dayOfBHD=" + dayOfBHD + "]";
	}

	private String name;
	private String lastName;
	private String gender;
	private String id;
	private double stature;
	private String nationality;
	private LocalDate dayOfBHD;
	public Image picture;
	public final static String MALE = "male";
	public final static String FEMALE = "female";
	public final static double MIN_STATURE = 50;
	public final static double MAX_STATURE = 300;
	
	public User(String name, String lastName,String id, String gender,double stature, String nationality, LocalDate dayOfBHD, Image picture) {
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

	public Image getPicture() {
		return picture;
	}
	
	public void setPicture(Image picture) {
		this.picture = picture;
	}

	public String getId() {
		return id;
	}
}
 