package Views;

import Class.*;
import Connection.ConnectDB;
import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PedidosView {

    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<Pedidos> tablaPedidos;
    @FXML
    private TableColumn<Pedidos,Integer> ColidPedido;
    @FXML
    private TableColumn<Pedidos,Integer> ColidUsuario;
    @FXML
    private TableColumn<Pedidos,Integer> ColidProducto;
    @FXML
    private TableColumn<Pedidos,Double> ColCantidad;
    @FXML
    private TableColumn<Pedidos,String> ColFecha;


    public void initialize() {
        ConnectDB.openConn();
        ColidPedido.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColidUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        ColidProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        ColCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        ColFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ObservableList<Pedidos> pedidos = FXCollections.observableArrayList(ModelPedidos.getPedidos());
        tablaPedidos.setItems(pedidos);
    }




}
