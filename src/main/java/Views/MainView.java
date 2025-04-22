package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {

    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnUsuarios;
    @FXML
    private Button btnProductos;

    public MainView() {}


    public void abrirVentana(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            //stage.setMaximized(true);
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pedidos(ActionEvent event){
        abrirVentana("/View/PedidoView.fxml","Pedidos");
        ((Stage) btnPedidos.getScene().getWindow()).close();
    }



}
