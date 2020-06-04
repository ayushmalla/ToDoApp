package controller;

	import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListCell;

import Animations.Shaker;
import database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Task;

	public class CellController extends JFXListCell<Task> {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private AnchorPane rootAnchorPane;

	    @FXML
	    private ImageView iconImageView;

	    @FXML
	    private Label taskLabel;

	    @FXML
	    private Label taskDescription;
	    
	    @FXML
	    private Label dateLabel;

	    @FXML
	    private ImageView deleteButton;
	    
	    @FXML
	    private ImageView UpdateButton;
	    

	    private FXMLLoader fxmlLoader;
	    
	    private DatabaseHandler databaseHandler;
	    
	    @FXML
	    void initialize() {
	       
	    }
	    @Override
	    public void updateItem(Task myTask, boolean empty) {
	    	super.updateItem(myTask, empty);
	    	
	    	if(empty || myTask == null) {
	    		setText(null);
	    		setGraphic(null);
	    	}else {
	    		if(fxmlLoader == null) {
	    			fxmlLoader = new FXMLLoader(getClass().getResource("/view/Cell.fxml"));
	    			fxmlLoader.setController(this);
	    			
	    			try {
						fxmlLoader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		taskLabel.setText(myTask.getTask());
	    		dateLabel.setText(myTask.getDatecreated().toString());
	    		taskDescription.setText(myTask.getDescription().toString());
	    		
	    		System.out.println("UserId from cell controller: "+AddItemController.userId);
	    		
	    		setText(null);
	    		setGraphic(rootAnchorPane);
	    		
	    		int taskId = myTask.getTaskId();
	    		
	    		UpdateButton.setOnMouseClicked(event -> {
	    			Shaker updateShake = new Shaker();
	    			updateShake.ShakeButtons(UpdateButton);
	    			updateShake.shake();
	    			
	                 FXMLLoader loader = new FXMLLoader();
	                 loader.setLocation(getClass().getResource("/view/UpdateTaskForm.fxml"));


	                  try {
	                      loader.load();
	                  } catch (IOException e) {
	                      e.printStackTrace();
	                  }

	                  Parent root = loader.getRoot();
	                  Stage stage = new Stage();
	                  stage.setScene(new Scene(root));

	                  UpdateTaskController updateTaskController = loader.getController();
	                  updateTaskController.setTaskField(myTask.getTask());
	                  updateTaskController.setUpdateDescriptionField(myTask.getDescription());

	                  updateTaskController.updateTaskButton.setOnAction(event1 -> {
	                	  	
	                      Calendar calendar = Calendar.getInstance();

	                      java.sql.Timestamp timestamp =
	                              new java.sql.Timestamp(calendar.getTimeInMillis());

	                      try {
	                    	  System.out.println("taskid: "+myTask.getTaskId());
	                    	  
							databaseHandler.updateTask(updateTaskController.getTask(), timestamp, updateTaskController.getDescription(), myTask.getTaskId());
						} catch (ClassNotFoundException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                     updateTaskController.updateTaskButton.getScene().getWindow().hide();

	                  });

	                  stage.show();


	              });

	    		 
	    		
		    	deleteButton.setOnMouseClicked(event ->{
		    		Shaker deleteShake = new Shaker();
		    		deleteShake.ShakeButtons(deleteButton);
		    		deleteShake.shake();
		    		
		    		databaseHandler = new DatabaseHandler();
		    		try {
		    			
						databaseHandler.deleteTask(AddItemController.userId,taskId);
					
		    		} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		getListView().getItems().remove(getItem());
		    		 
		    	});
	    		
	    	

    		
	    }
	    	}
	    	
	    }
	   

