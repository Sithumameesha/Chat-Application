package controller;

import com.sun.prism.paint.Color;
import com.sun.xml.internal.messaging.saaj.soap.impl.TextImpl;
import javafx.application.Platform;
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
import model.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    public ScrollPane main;
    public VBox vbox_msgArea;
    public TextField txtMsg;
    public Button btnSend;
    private Server server;

    public static void addLabel(String msgFromClient,VBox vBox){
        HBox hBox= new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));

        Text text = new Text(msgFromClient);
        TextFlow textFlow= new TextFlow(text);
        textFlow.setStyle("-fx-background-color: rgb(233,233,255);\"+\" -fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5,5,5,10));
        hBox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);

            }
        });

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            server = new Server(new ServerSocket(8000));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Creating Server");
        }
        vbox_msgArea.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //main.receiveClientMsg(vbox_msgArea);
                main.setVvalue((Double)newValue);

            }
        });
        server.receiveClientMsg(vbox_msgArea);

    }
    public void sendOnAction(ActionEvent actionEvent) {
        String message =txtMsg.getText();
        if (!message.isEmpty()){
            HBox hBox=new HBox();
            hBox.alignmentProperty();
            hBox.setPadding(new Insets(5,5,5,10));

            Text text= new Text(message);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle(" -fx-background-color: rgb(233,233,255);\"+\" -fx-background-radius: 20px");
            textFlow.setPadding(new Insets(5,5,5,10));
            //text.setFill(new Color(0.934,0.945,0.996));
            hBox.getChildren().add(textFlow);
            vbox_msgArea.getChildren().add(hBox);
            server.sendMsgClient(message);
            txtMsg.clear();
            }
        }




}
