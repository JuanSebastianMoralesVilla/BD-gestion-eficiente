package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataBase;

public class Main  extends Application{

	private DataBaseGUI dbGUI;
	private DataBase miDataBase;
	
	
public Main() throws IOException {
	// TODO Auto-generated constructor stub
	
	miDataBase= new DataBase();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
launch (args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		dbGUI= new DataBaseGUI(stage, miDataBase);
		//FileInputStream fis = new FileInputStream("Images/");
		FXMLLoader f = new FXMLLoader(getClass().getResource("InterfazMenu.fxml"));
		f.setController(dbGUI);
		Parent root = f.load();
		Scene sc = new Scene(root);
		stage.setScene(sc);
		stage.sizeToScene();
		sc.getStylesheets().add(getClass().getResource("iconos.css").toExternalForm());
		stage.setScene(sc);
		stage.centerOnScreen();
		stage.setResizable(true);
		stage.setTitle("BD VIP");
		stage.show();
		
		
	}

}
