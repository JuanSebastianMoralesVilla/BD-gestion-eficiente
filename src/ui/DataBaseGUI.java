package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javafx.scene.control.*;

import custom_exceptions.InvalidValueException;
import custom_exceptions.ValuesIsEmptyException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import model.DataBase;
import model.User;
import threads.CreatingDataThread;
import threads.ProgressThread;

public class DataBaseGUI {

	// --------------------
	// Relaciones
	// --------------------
	private DataBase database;
	private User user;

	// --------------------
	// Atributos
	// ---------------------

	int numData;

	// --------------------
	// elementos de la gui
	// ---------------------

	    @FXML
	    private Button btnImage;


	@FXML
    private Tab wGenerateData;
	
    @FXML
    private Tab wAddUser;
    @FXML
    private Tab wSearch;
    @FXML
    private Tab wUpdate;

	@FXML
	private ProgressBar lineProgress;

	@FXML
	private Label ProgressLabel;

	@FXML
	private Button btSaveData;

	@FXML
	private Button btGenerateData;
	
	@FXML
	private TextField txtHeightA;

	@FXML
	private TextField txtCantGenerate;

	@FXML
	private TextField txtTimeGen;

	@FXML
	private RadioButton tgFemale;

	@FXML
	private ToggleGroup tgGender;

	@FXML
	private RadioButton tgMale;

	@FXML
	private DatePicker txtDateBHD;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtName;

	@FXML
	private TextField btCodigo;

	@FXML
	private TextField txtHeight;

	@FXML
	private Button btAddUser;

	@FXML
	private ImageView imageviewLoad;

	@FXML
	private Button btImage;

	@FXML
	private Button btBuscar;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TableView<User> tableUsers;

	@FXML
	private TableColumn<User, String> cvID;

	@FXML
	private TableColumn<User, String> cvName;

	@FXML
	private TableColumn<User, String> cvLastName;

	@FXML
	private Button btActualizeData;

	@FXML
	private RadioButton tgFemale1;

	@FXML
	private ToggleGroup tgGenderA;

	@FXML
	private RadioButton tgMale1;

	@FXML
	private DatePicker txtDateBHDA;

	@FXML
	private TextField txtLastNameA;

	@FXML
	private TextField txtNameA;

	@FXML
	private TextField btCodigoA;

	@FXML
	private Button btConfirmUser;

	@FXML
	private Button btDeleteUser;
	
	@FXML
    private ImageView txtImageA;
	
    @FXML
    private ComboBox<String> comboBoxNationality;

	
    @FXML
    private ComboBox<String> comboBoxNationalityA;


	@FXML
	private ComboBox<String> comboBoxSearch;
	
	
	
    @FXML
    private Label lbImageUpdate;
    @FXML
    private Label lbImage;

	// --------------------
	// constructor
	// --------------------
	public DataBaseGUI(Stage stage, DataBase dataBase) {
		// TODO Auto-generated constructor stub
		this.database = dataBase;

	}
	
	@FXML
	void UpdateImage(ActionEvent event) {
		
		Image image = database.generatePicture();
		
		ImageView view = new ImageView(image);
		view.setFitHeight(120);
		view.setFitWidth(120);
	    lbImageUpdate.setGraphic(view);
	    lbImageUpdate.setText("");
	    user.setPicture(image);

		

	}
	
    @FXML
    void imageAdd(ActionEvent event) {

    }

	@FXML
	public void initialize() throws Exception {
		comboBoxSearch.getItems().removeAll(comboBoxSearch.getItems());
		comboBoxSearch.getItems().addAll(DataBase.NAME, DataBase.LAST_NAME, DataBase.ID,DataBase.FULL_NAME);
		comboBoxNationality.getItems().removeAll(comboBoxNationality.getItems());
		comboBoxNationalityA.getItems().removeAll(comboBoxNationalityA.getItems());
		comboBoxNationality.getItems().addAll("United States","Brazil","Mexico","Colombia","Argentina","Canada","Peru","Venezuela","Chile","Guatemala","Ecuador","Bolivia","Haiti","Cuba","Dominican Republic","Honduras","Paraguay","Nicaragua","El Salvador","Costa Rica","Panama","Uruguay","Jamaica","Trinidad and Tobago","Guyana","Suriname","Belize","Bahamas","Barbados","Saint Lucia","Grenada","Antigua and Barbuda","Dominica","Saint Kitts" );
		comboBoxNationalityA.getItems().addAll("United States","Brazil","Mexico","Colombia","Argentina","Canada","Peru","Venezuela","Chile","Guatemala","Ecuador","Bolivia","Haiti","Cuba","Dominican Republic","Honduras","Paraguay","Nicaragua","El Salvador","Costa Rica","Panama","Uruguay","Jamaica","Trinidad and Tobago","Guyana","Suriname","Belize","Bahamas","Barbados","Saint Lucia","Grenada","Antigua and Barbuda","Dominica","Saint Kitts" );
		comboBoxSearch.getSelectionModel().select(DataBase.NAME);
		comboBoxNationality.getSelectionModel().select("Colombia");
		comboBoxNationalityA.getSelectionModel().select("Colombia");
		database.createUser("Cristian", "Morales", "male" , 123, "Colombia", LocalDate.now() );
		database.createUser("Santiago", "Hurtado", "male" , 123, "Colombia", LocalDate.now());
		database.createUser("Cristina", "Martin", "female" , 123, "Colombia", LocalDate.now());
		database.createUser("Cristoval", "Huertas", "male" , 123, "Colombia", LocalDate.now());
		btSaveData.setDisable(true);
		wUpdate.setDisable(true);
	}

	// --------------------
	// Metodos de clase GUI
	// ---------------------

	@FXML
	void addUser(ActionEvent event) throws Exception {
		
		try {
		String name = txtName.getText();
		String lastName = txtLastName.getText();
		String gender = "";		
		if (tgFemale.isSelected()) {
			gender = User.FEMALE;
		}else {
			gender = User.MALE;
		}		
		String height = txtHeight.getText();		
		LocalDate dayOfBHD = txtDateBHD.getValue();
		String nationality = comboBoxNationality.getValue();
		String picture = "";
		
		if (name.equals("")|| lastName.equals("") || height.equals("") || dayOfBHD.equals("")) {
			JOptionPane.showMessageDialog(null, "por favor complete todos los campos");
		}
		double stature = Double.parseDouble(height);
		
		User user= database.createUser(name, lastName, gender, stature, nationality, dayOfBHD);
		ImageView view = new ImageView(user.picture);
		view.setFitHeight(120);
		view.setFitWidth(120);
	    lbImage.setGraphic(view);
	    lbImage.setText("");
		
		JOptionPane.showMessageDialog(null,"el usuario "+ user.getName()+ " "+ user.getLastName() + "\n"+ "con codigo "+ user.getId()+ "\n"+ "fue creado exitosamente");
		

		
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ingrese una estatura valida");
			// TODO: handle exception
		}
		

	}

	@FXML
	void confirmUdpateUser(ActionEvent event) {
		try {
		String name = txtNameA.getText();
		String lastName = txtLastNameA.getText();
		String gender = "";		
		if (tgFemale1.isSelected()) {
			gender = User.FEMALE;
		}else {
			gender = User.MALE;
		}		
		String height = txtHeightA.getText();		
		LocalDate dayOfBHD = txtDateBHDA.getValue();
		String nationality = comboBoxNationalityA.getValue();
		String picture = "";
		
		if (name.equals("")|| lastName.equals("") || height.equals("") || dayOfBHD.equals("")) {
			JOptionPane.showMessageDialog(null, "por favor complete todos los campos");
		}
		System.out.println(height);
		double stature = Double.parseDouble(height);
		
		database.updateUser(user, name, lastName, gender, stature, nationality, dayOfBHD, user.picture);
		ImageView view = new ImageView(user.picture);
		view.setFitHeight(120);
		view.setFitWidth(120);
	    lbImageUpdate.setGraphic(view);
	    lbImageUpdate.setText("");
		
		JOptionPane.showMessageDialog(null,"el usuario "+ user.getName()+ " "+ user.getLastName() + "\n"+ "con codigo "+ user.getId()+ "\n"+ "fue actualizado exitosamente");
		

		
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "ingrese una estatura valida");
			// TODO: handle exception
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ingrese una fdsa valida");
			// TODO: handle exception
		} 

	}

	@FXML
	void deleteUser(ActionEvent event) {

	}

	@FXML
	void generateData(ActionEvent event) {
		try {
			numData = Integer.parseInt(txtCantGenerate.getText());
			if (numData < 0) {
				JOptionPane.showMessageDialog(null, "El numero ingresado debe ser mayor a cero");
			} else {
				ProgressThread pt = new ProgressThread(database, this);
				database.setCreating(true);
				pt.start();
				CreatingDataThread cd = new CreatingDataThread(database, numData);
				cd.start();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Debes ingresar un numero valido");
		}
	}

	@FXML
	void saveData(ActionEvent event) {
		database.saveInAVL();
		database.setCreating(true);
		ProgressThread pt = new ProgressThread(database, this);
		pt.start();
		
	}
	
	@FXML
	private TabPane TabPane;
	
	@FXML
	void updateUser(ActionEvent event) {
		TabPane.getSelectionModel().select(wUpdate);
		user= tableUsers.getSelectionModel().getSelectedItem();
		if(user!=null) {
		
			wUpdate.setDisable(false);
			
			btCodigoA.setText(user.getId());
			String gender = user.getGender();
			if(gender.equals(User.MALE)) {
				tgMale1.setSelected(true);
				tgFemale1.setSelected(false);
			}else {
				tgMale1.setSelected(false);
				tgFemale1.setSelected(true);
			}
			txtDateBHDA.setValue(user.getDayOfBHD());
			txtNameA.setText(user.getName());
			txtLastNameA.setText(user.getLastName());
			comboBoxNationalityA.setValue(user.getNationality());
			txtHeightA.setText(user.getStature()+"");
			
			ImageView view = new ImageView(user.picture);
			view.setFitHeight(120);
			view.setFitWidth(120);
		    lbImageUpdate.setGraphic(view);
		    lbImageUpdate.setText("");
		}
		
		
	}
    @FXML
    void searchUser(ActionEvent event) {
    	String searchType = comboBoxSearch.getValue();
		String value = txtBuscar.getText();
		ArrayList<User> users = database.sensitiveSearch(value,searchType);
		
		ObservableList<User> observableList;
		System.out.println(users);
		observableList = FXCollections.observableArrayList(users);
		tableUsers.setItems(observableList);
		
		cvID.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		cvName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		cvLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
    }
    

	public void setProgressBar() {
		double start = System.currentTimeMillis();
		btGenerateData.setDisable(true);
		wGenerateData.setDisable(true);
		wAddUser.setDisable(true);
		wSearch.setDisable(true);
		wUpdate.setDisable(true);
		btSaveData.setDisable(true);
		
		while(database.isCreating()) {
			lineProgress.setProgress(database.getSeachingAvance());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		wGenerateData.setDisable(false);
		wAddUser.setDisable(false);
		wSearch.setDisable(false);
		wUpdate.setDisable(false);
		btSaveData.setDisable(false);
		btGenerateData.setDisable(false);
		double end = System.currentTimeMillis();
		txtTimeGen.setText((end-start)+"");
	}
	@FXML
    void sensitiveSearch(KeyEvent event) {
    	String searchType = comboBoxSearch.getValue();
		String value = txtBuscar.getText().toUpperCase();
		ArrayList<User> users = database.sensitiveSearch(value,searchType);
		
		ObservableList<User> observableList;
		System.out.println(users);
		observableList = FXCollections.observableArrayList(users);
		tableUsers.setItems(observableList);
		
		cvID.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		cvName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		cvLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
    }
	
    @FXML
    void sensitiveSearchKeyR(KeyEvent event) {
    	/*
    	String searchType = comboBoxSearch.getValue();
		String value = txtBuscar.getText();
		ArrayList<User> users = database.sensitiveSearch(value,searchType);
		
		ObservableList<User> observableList;
		System.out.println(users);
		observableList = FXCollections.observableArrayList(users);
		tableUsers.setItems(observableList);
		
		cvID.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		cvName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		cvLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		*/
    }
	
    @FXML
    void sensitiveSearchKeyT(KeyEvent event) {
    	/*
    	String searchType = comboBoxSearch.getValue();
		String value = txtBuscar.getText();
		ArrayList<User> users = database.sensitiveSearch(value,searchType);
		
		ObservableList<User> observableList;
		System.out.println(users);
		observableList = FXCollections.observableArrayList(users);
		tableUsers.setItems(observableList);
		cvID.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		cvName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		cvLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		*/
    }

}
