package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Alert.Information;
import Animations.Shaker;
import database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.User;

public class LoginController {
	private int userId;

	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private JFXTextField loginUsername;

	    @FXML
	    private JFXButton loginButton;

	    @FXML
	    private JFXPasswordField loginPassword;

	    @FXML
	    private JFXButton loginSignupButton;
	   
	    private Information info;
	    @FXML
	    void initialize() {
	    	DatabaseHandler dbHandler = new DatabaseHandler();
	    	
	    	
	    	
	    	loginButton.setOnAction(event ->{
	    		String loginText = loginUsername.getText().trim();
		    	String loginPwd = loginPassword.getText().trim();
		    	
		    	User user = new User();
		    	user.setUserName(loginText);
		    	user.setPassword(loginPwd);
	    		ResultSet userRow = dbHandler.getUser(user);
	    		
	    		int counter = 0;
	    		try {
					while(userRow.next()) {
						counter++;
						String name = userRow.getString("firstname");
						userId = userRow.getInt("userid");
						
						System.out.println("Welcome"+name);
					}
					
					if(counter==1){
						
						//alert box for logged in
						info = new Information();
						info.loginAlert();
						
		    			showAddItemScreen();
		    			
						}else {
		    			Shaker usernameshaker = new Shaker(loginUsername);
		    			Shaker passwordshaker = new Shaker(loginPassword);
		    			usernameshaker.shake();
		    			passwordshaker.shake();
		    		}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				
	    		
	    		
	    		
	    	});
	    	
	    	loginSignupButton.setOnAction(event ->{
	    		//takes users to signup screen
	    		loginSignupButton.getScene().getWindow().hide();
	    		FXMLLoader loader = new FXMLLoader();
	    		
	    		try {
					loader.setLocation(getClass().getResource("/view/signup.fxml"));
					loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		Parent root = loader.getRoot();
	    		Stage stage = new Stage();
	    		stage.setScene(new Scene(root));
	    		stage.showAndWait();
	    		 
	    		
	    		
	    	});
	    	
	       
	    }
	    public void showAddItemScreen() {
	    	//it will take us to additem screen
	    	loginButton.getScene().getWindow().hide();
    		FXMLLoader loader = new FXMLLoader();
    		
    		try {
    			loader.setLocation(getClass().getResource("/view/addItem.fxml"));
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Parent root = loader.getRoot();
    		Stage stage = new Stage();
    		stage.setScene(new Scene(root));
    		
    		AddItemController addItemController = loader.getController();
    		addItemController.setUserId(userId);
    		
    		
    		stage.showAndWait();
	    }
	    
}
