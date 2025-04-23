package Views;

import Connection.ConnectDB;
import Models.ModelProductos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Class.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;


public class ProductosView {
    @FXML
    private TextField TextBuscar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAtras;
    @FXML
    private TableView<Productos> TablaProductos;
    @FXML
    private TableColumn<Productos, Integer> ColidProducto;
    @FXML
    private TableColumn<Productos, String> ColProducto;
    @FXML
    private TableColumn<Productos, Double> ColPrecio;
    @FXML
    private TableColumn<Productos, Integer> ColidCategoria;


    public void initialize() {
        ConnectDB.openConn();
        ColidProducto.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        ColidCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        ObservableList<Productos> productos = FXCollections.observableArrayList(ModelProductos.getProductos());
        TablaProductos.setItems(productos);
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
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atras(ActionEvent event){
        abrirVentana("/View/Main.fxml","Menu Principal");
        ((Stage) btnAtras.getScene().getWindow()).close();
    }

}
