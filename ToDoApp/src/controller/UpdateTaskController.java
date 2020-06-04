package controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UpdateTaskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField updateTaskField;

    @FXML
    private JFXTextField updateDescriptionField;

    @FXML
    public Button updateTaskButton;

    @FXML
    void initialize() {
      
    }
    public void setTaskField(String task) {
        this.updateTaskField.setText(task);
    }

    public String getTask() {
        return this.updateTaskField.getText().trim();
    }

    public void setUpdateDescriptionField(String description) {
        this.updateDescriptionField.setText(description);
    }

    public String getDescription() {
        return this.updateDescriptionField.getText().trim();
    }
}
