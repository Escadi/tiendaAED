package Views;

import Class.*;
import Connection.ConnectDB;
import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class PedidosView {

    @FXML
    private TextField TextBuscar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAtras;
    @FXML
    private HBox hboxMod;
    @FXML
    private TableView<Pedidos> tablaPedidos;
    @FXML
    private TableColumn<Pedidos,Integer> ColidPedido;
    @FXML
    private TableColumn<Pedidos,Integer> ColidUsuario;
    @FXML
    private TableColumn<Pedidos,Integer> ColidProducto;
    @FXML
    private TableColumn<Pedidos,Integer> ColCantidad;
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

        Popup pop = new Popup();
        pop.getContent().add(hboxMod);
        tablaPedidos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Pedidos pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
                if (pedidoSeleccionado != null) {
                    pop.show(
                            tablaPedidos.getScene().getWindow(),
                            event.getSceneX(),
                            event.getSceneY()
                    );
                    hboxMod.setVisible(true);

                }
            }
        });


    }

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
