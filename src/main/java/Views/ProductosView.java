package Views;

import Connection.ConnectDB;
import Models.ModelProductos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import Class.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ProductosView {
    @FXML
    private TextField TextBuscar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
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

}
