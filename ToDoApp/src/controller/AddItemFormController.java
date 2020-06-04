package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Alert.Information;
import Animations.Shaker;
import database.DatabaseHandler;

import java.sql.Timestamp;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Task;
import model.User;

public class AddItemFormController {
	private DatabaseHandler databaseHandler;
	
	private int userId;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField taskfield;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;
    
    @FXML
    private Label successLabel;

    @FXML
    private JFXButton todosButton;
    
    @FXML
    private JFXButton logOutButton;
    
    @FXML
    private AnchorPane closePane;
    @FXML
    void initialize() {
      databaseHandler = new DatabaseHandler();
      
     
      
      saveTaskButton.setOnAction(event ->{
	    	Task task = new Task();
	    	
	    	Calendar calender = Calendar.getInstance();
	    	
	    	java.sql.Timestamp timeStamp = new java.sql.Timestamp(calender.getTimeInMillis()); 
	    	task.setDatecreated(timeStamp);
	    	
	    	String taskText = taskfield.getText().trim();
	    	String description = descriptionField.getText().trim();
	    	
	    	if(!taskText.equals("") || !description.equals("")) {
	    		System.out.println("User id:"+AddItemController.userId);
	    		
	    		task.setUserId(AddItemController.userId);
	    		task.setDatecreated(timeStamp );
	    		task.setTask(taskText);
		    	task.setDescription(description);
		    	
		    	databaseHandler.inserTask(task);
		    	
		    	//dialog box for successfully task creation calling taskAlert method
		    	
				Information info = new Information();
				info.taskAlert();
				
		    	todosButton.setVisible(true);
		    
		    	int taskNumber = 0;
		    	
		    	try {
					taskNumber = databaseHandler.getAllTasks(AddItemController.userId);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	todosButton.setText("My 2DO's ("+taskNumber+")");
		    	
		    
		    	
		    	taskfield.setText("");
		    	descriptionField.setText("");
		    	
		    	todosButton.setOnAction(event1 ->{
		    		//send user to list Screen
		    		FXMLLoader loader = new FXMLLoader();
		    		loader.setLocation(getClass().getResource("/view/List.fxml"));
		    		try {
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
		    	
	    	}else {
	    		System.out.println("Nothing added");
	    	}
	    	
	    	
      });
      
      logOutButton.setOnAction(event ->{
    	  Alert alert = new Alert(AlertType.CONFIRMATION);
    	  alert.setTitle("LOG OUT");
    	  alert.setHeaderText("You wanna Log Out");
    	  alert.setContentText("Are you ok with this?");

    	  Optional<ButtonType> result = alert.showAndWait();
    	  if (result.get() == ButtonType.OK){
    		 
    		  logOutButton.getScene().getWindow().hide();
    		  
    		  LogOut();
    		  
	    		
    	  } else {
    		  alert.close();
    	  }
    	  
      });
    	
    }
    
    public int getUserId() {
    	return this.userId;
    }
    
    public void setUserId(int userId) {
    	this.userId = userId;
    	System.out.println("UserId is:"+this.userId);
    }
    
    public void LogOut() {
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
    }
   
}
