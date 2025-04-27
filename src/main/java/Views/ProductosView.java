package Views;

import Connection.ConnectDB;
import Models.ModelProductos;
import javafx.application.Platform;
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



public class ProductosView {
    private MainView mainView = new MainView();
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



    public void atras(ActionEvent event){
        mainView.abrirVentana("/View/Main.fxml","Menu Principal");
        ((Stage) btnAtras.getScene().getWindow()).close();
    }

}
