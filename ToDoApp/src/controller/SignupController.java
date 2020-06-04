package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.image.impl.ByteIndexed.Getter;

import Alert.Information;
import application.Main;
import database.DatabaseHandler;

public class SignupController {
	   @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private JFXTextField signUpFirstName;

	    @FXML
	    private JFXTextField signUpLastName;

	    @FXML
	    private JFXTextField signUpUsername;
	    
	    @FXML
	    private JFXPasswordField signUpPassword;

	    @FXML
	    private JFXCheckBox signUpcheckBoxMale;

	    @FXML
	    private JFXCheckBox signUpcheckBoxFemale;
	    
	    @FXML
	    private JFXTextField signUpLocation;

	    @FXML
	    private JFXButton signUpButton;
	    
	    @FXML
	    private JFXButton loginButton;
	    
	    private Information infor;
	    @FXML
	    void initialize() {
	    	
	    	
	    	signUpButton.setOnAction(event -> {
	    		//signup dialog box
	    		infor = new Information();
	    		infor.signAlert();
	    		
	    		createUser();
	    		
	    		
	    	});
	    	
	    	loginButton.setOnAction(event ->{
	    		
	    		
	    		signUpButton.getScene().getWindow().hide();
	    	
	    		
	    		
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(getClass().getResource("/view/login.fxml"));
	    		try {
					loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		Parent root = loader.getRoot();
	    		Stage stage = new Stage();
	    		stage.setScene(new Scene(root));
	    		stage.show();
	    	});
	    
	    }
	   private void createUser() {
		   DatabaseHandler databaseHandler = new DatabaseHandler();
		   String fname = signUpFirstName.getText();
		   String lname = signUpLastName.getText();
		   String uname = signUpUsername.getText();
		   String pwd = signUpPassword.getText();
		   String location = signUpLocation.getText();
		   
		   String gender= "";
		   if(signUpcheckBoxFemale.isSelected()) {
			   gender = "Female";
		   }else {
			   gender = "Male";
		   }
		   User user = new User(fname,lname,uname,pwd,location,gender);
		   
		   databaseHandler.signUpUser(user);
	   }
}
