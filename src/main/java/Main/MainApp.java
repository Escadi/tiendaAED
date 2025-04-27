package Main;

import Connection.ConnectDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * TiendaAED, Aplicación de Tiendas
 * <p>
 * Creadores de la aplicación:
 *
 * @author David Liaño Macíasº
 * @author Eliu Manuel Viera Lorenzo
 * @author Erico
 * @version 1.0
 */

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/View/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    public static void main(String[] args) {
        ConnectDB.openConn();
        launch(args);
    }

}
