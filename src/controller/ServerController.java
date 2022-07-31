package controller;

import com.sun.corba.se.spi.activation.Server;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    public ScrollPane main;
    public VBox vbox_msgArea;
    public TextField txtMsg;
    public Button btnSend;


    public void sendOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
