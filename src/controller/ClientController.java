package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Client;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public ScrollPane main;
    public VBox vbox_msg;
    public TextField txtMsg;
    public Button btnSend;
    private Client client;

    public void sendOnAction(ActionEvent actionEvent) {
        String message =txtMsg.getText();
        if (!message.isEmpty()){
            HBox hBox=new HBox();
            //hBox.alignmentProperty(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5,6,5,10));

            Text text= new Text(message);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle(" -fx-background-color: rgb(233,233,255);\"+\" -fx-background-radius: 20px");
            textFlow.setPadding(new Insets(5,5,5,10));
            //text.setFill(new Color(0.934,0.945,0.996));
            hBox.getChildren().add(textFlow);
            vbox_msg.getChildren().add(hBox);
            client.sendMsgServer(message);
            txtMsg.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            client = new Client(new Socket("localhost", 8000));
            System.out.println("Connected to Sever.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        vbox_msg.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                main.setVvalue((Double) newValue);
            }
        });

        client.receivemsgSever(vbox_msg);
    }
}
