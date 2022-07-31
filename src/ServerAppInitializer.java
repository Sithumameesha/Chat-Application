import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ServerAppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("view/server.fxml"));
        Scene scene= new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }}
