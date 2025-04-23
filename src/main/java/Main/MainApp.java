package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/View/Main.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();


    }


    public static void main(String[] args) {

        launch(args);
    }
}
