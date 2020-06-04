package Animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fader {
	private FadeTransition fadeTransition;
	
	public Fader() {
		
	}
	
	//constructor
	public Fader(Node node) {
		 fadeTransition = new FadeTransition(Duration.millis(2000),node);
		
		 fadeTransition.setFromValue(1f);
    	 fadeTransition.setToValue(0f);
    	 fadeTransition.setCycleCount(1);
    	 fadeTransition.setAutoReverse(false);
    	 fadeTransition.play();
	}
	
	public void fadeAppear(Node node) {
		fadeTransition = new FadeTransition(Duration.millis(2000),node);
		
		 fadeTransition.setFromValue(0f);
		 fadeTransition.setToValue(1f);
		 fadeTransition.setCycleCount(1);
		 fadeTransition.setAutoReverse(false);
		 fadeTransition.play();
	}
}
