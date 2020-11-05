package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.DataBase;

public class DataBaseGUI {

	// --------------------
	// Relaciones
	// --------------------
	private DataBase database;

	// --------------------
	// constructor
	// --------------------
	public DataBaseGUI(Stage stage, DataBase dataBase) {
		// TODO Auto-generated constructor stub
		this.database = dataBase;
	}

	// --------------------
	// elementos de la gui
	// ---------------------

	@FXML
	private ProgressBar lineProgress;

	@FXML
	private Button btSaveData;

	@FXML
	private Button btGenerateData;

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
	private Spinner<?> txtHeight;

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
	private TableView<?> tableUsers;

	@FXML
	private TableColumn<?, ?> cvID;

	@FXML
	private TableColumn<?, ?> cvName;

	@FXML
	private TableColumn<?, ?> cvLastName;

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
	private Spinner<?> txtHeightA;

	@FXML
	private Button btConfirmUser;

	@FXML
	private Button btDeleteUser;
	
	 // --------------------
	// Metodos de clase GUI
   // ---------------------

	@FXML
	void addUser(ActionEvent event) {

	}

	@FXML
	void confirmUdpateUser(ActionEvent event) {

	}

	@FXML
	void deleteUser(ActionEvent event) {

	}

	@FXML
	void generateData(ActionEvent event) {

	}

	@FXML
	void saveData(ActionEvent event) {

	}

	@FXML
	void searchUser(ActionEvent event) {

	}

	@FXML
	void updateUser(ActionEvent event) {

	}

}
