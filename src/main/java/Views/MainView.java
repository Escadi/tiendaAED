package Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Functions.CloseWindows;

import java.io.IOException;

public class MainView {
    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnUsuarios;
    @FXML
    private Button btnProductos;
    @FXML
    private VBox vboxMain;

    public MainView() {
    }

    public void initialize() {

        if (vboxMain != null) {
            vboxMain.setFillWidth(true);
        }

    }


    /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                        Botones de la interfaz                                                  |
   +----------------------------------------------------------------------------------------------------------------+
   */

    public void abrirVentana(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.setTitle(titulo);
            CloseWindows.cerrarVentanas(stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pedidos(ActionEvent event) {
        abrirVentana("/View/PedidoView.fxml", "Pedidos");
        ((Stage) btnPedidos.getScene().getWindow()).close();
    }

    public void usuarios(ActionEvent event) {
        abrirVentana("/View/UsuarioView.fxml", "Usuarios");
        ((Stage) btnUsuarios.getScene().getWindow()).close();
    }

    public void productos(ActionEvent event) {
        abrirVentana("/View/ProductoView.fxml", "Productos");
        ((Stage) btnProductos.getScene().getWindow()).close();
    }


}
