package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import Animations.Shaker;
import database.DatabaseHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import model.Task;

public class ListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXListView<Task> listTask;

    @FXML
    private JFXTextField listTaskfield;

    @FXML
    private JFXTextField listDescriptionField;

    @FXML
    private JFXButton listSaveTaskButton;
    
    @FXML
    private ImageView listRefreshButton;
   
    private DatabaseHandler databaseHandler;
    //creating list
    ObservableList<Task> tasks;
    ObservableList<Task> refreshTask;
    		
    

    @FXML
    void initialize() throws SQLException {
    	tasks = FXCollections.observableArrayList();
    	  	
    	databaseHandler = new DatabaseHandler();
    	ResultSet resultSet = databaseHandler.getTaskByUser(AddItemController.userId);
    	
    	while(resultSet.next()) {
    		Task task = new Task();
    		task.setTaskId(resultSet.getInt("taskid"));
    		task.setTask(resultSet.getString("task"));
    		task.setDatecreated(resultSet.getTimestamp("datecreated"));
    		task.setDescription(resultSet.getString("description"));
    		

        	tasks.add(task);
    	}
    	
//    	Task myTask = new Task();
//    	
//    	myTask.setTask("Clear car");
//    	myTask.setDescription("HOw to do");
//    	myTask.setDatecreated(Timestamp.valueOf(LocalDateTime.now()));
    	
    	
    	
    	listTask.setItems(tasks);
    	
    	listTask.setCellFactory(CellController -> new CellController());
    	
//        listTask.setItems(listview);
//        listTask.setCellFactory(param -> new JFXCell());
    	listRefreshButton.setOnMouseClicked(event ->{
    		try {
    			Shaker refreshShake = new Shaker();
    			refreshShake.ShakeButtons(listRefreshButton);
    			refreshShake.shake();
    			
				refreshList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	});
    	
    	listSaveTaskButton.setOnAction(event ->{
    		addNewTask();
    		
    		
    	});
    }
   
    		public void refreshList() throws SQLException {
    			refreshTask = FXCollections.observableArrayList();
        	  	
    	    	databaseHandler = new DatabaseHandler();
    	    	ResultSet resultSet = databaseHandler.getTaskByUser(AddItemController.userId);
    	    	
    	    	while(resultSet.next()) {
    	    		Task task = new Task();
    	    		task.setTaskId(resultSet.getInt("taskid"));
    	    		task.setTask(resultSet.getString("task"));
    	    		task.setDatecreated(resultSet.getTimestamp("datecreated"));
    	    		task.setDescription(resultSet.getString("description"));
    	    		

    	        	refreshTask.addAll(task);
    		}
    	listTask.setItems(refreshTask);
    	listTask.setCellFactory(CellController -> new CellController());
    } 
    
  		 public void addNewTask() {
    	    		if(!listTaskfield.getText().equals("") || !listDescriptionField.getText().equals("")) {
    	    			

    	    			Task myNewTask = new Task();
    	    			
    	    			Calendar calender = Calendar.getInstance();
    	    	    	
    	    	    	java.sql.Timestamp timeStamp = new java.sql.Timestamp(calender.getTimeInMillis()); 
    	    	    	
    			    	
    			    	myNewTask.setUserId(AddItemController.userId);
    	    			myNewTask.setTask(listTaskfield.getText().trim());
    	    			myNewTask.setDescription(listDescriptionField.getText().trim());
    	    			myNewTask.setDatecreated(timeStamp);
    	    			
    	    			databaseHandler.inserTask(myNewTask);
    	    			
    	    			//on click the field will be blank
    	    			listTaskfield.setText("");
    	        		listDescriptionField.setText("");
    	        		
    	        		try {
    						initialize();
    					} catch (SQLException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    	    		}
    	    }
    
}
