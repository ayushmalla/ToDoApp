package Alert;

import controller.SignupController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;


public class Information {
	
	public Information() {
	}
	public void taskAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Task Successfully Created!");

		alert.showAndWait();
	}
	public void loginAlert() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(" Logged In Successfully!");

		alert.showAndWait();
	}
	public void signAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Signed Up Successfully!");

		alert.show();
	}
}
