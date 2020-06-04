package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Animations.Fader;
import Animations.Shaker;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AddItemController {
	public static int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane rootAnchorPane;
    
    @FXML
    private ImageView addButton;

    @FXML
    private Label notTaskLabel; 

    @FXML
    void initialize() {
     addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
    	 Shaker additem = new Shaker(addButton);
    	 additem.shake();
    	 
    	 Fader buttonFadeTransition = new Fader(addButton);
    	 
    	 Fader notTaskLabelFade = new Fader(notTaskLabel);
    	 
    	 //remove
    	 addButton.relocate(0, 20);
    	 
    	notTaskLabel.setOpacity(0);
    	 
    	 
    	 
 	 try {
			AnchorPane formPane = FXMLLoader.load(getClass().getResource("/view/AddItemForm.fxml"));
			
			AddItemController.userId = getUserId();
			
			
//			AddItemFormController addItemFormController = new AddItemFormController();
//			addItemFormController.setUserId(getUserId());
			 
			Fader rootFadeTransition = new Fader();
			rootFadeTransition.fadeAppear(formPane);
			
	    	 
	    	 
			rootAnchorPane.getChildren().setAll(formPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     });
    }
    
    public void setUserId(int userId) {
    	this.userId = userId;
    	System.out.println("UserId is:"+this.userId);
    }
    
    public int getUserId() {
    	
    	return this.userId;
    }
}
