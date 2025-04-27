package Views;

import Class.*;


import Controller.PedidosController;
import Functions.ExportXML;
import Models.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Optional;

public class PedidosView {
    @FXML
    public Button btnConfirmar;
    @FXML
    public Button btnCerrar;
    private PedidosController pedidosController = new PedidosController();
    private MainView mainView = new MainView();
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TextField TextBuscar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAgregarPedido;
    @FXML
    private Button btnExportXML;
    @FXML
    private Button btnAtras;
    @FXML
    private VBox vboxModificarPedido;
    @FXML
    private HBox hboxBarraPedido;
    @FXML
    private TableView<Pedidos> tablaPedidos;
    @FXML
    private TableColumn<Pedidos, Integer> ColidPedido;
    @FXML
    private TableColumn<Pedidos, Integer> ColidUsuario;
    @FXML
    private TableColumn<Pedidos, Integer> ColidProducto;
    @FXML
    private TableColumn<Pedidos, Integer> ColCantidad;
    @FXML
    private TableColumn<Pedidos, String> ColFecha;
    @FXML
    private TextField textFieldIdUsuarioJoin;
    @FXML
    private TextField textFieldIdPedidoJoin;
    @FXML
    private TextField textFieldIdProductoJoin;
    @FXML
    private TextField textFieldCantidadJoin;
    @FXML
    private TextField textFieldFechaJoin;


    public void initialize() {
        ColidPedido.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColidUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        ColidProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        ColCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        ColFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ObservableList<Pedidos> pedidos = FXCollections.observableArrayList(ModelPedidos.getPedidos());
        tablaPedidos.setItems(pedidos);

       /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                     Posicionamiento de los botones                                             |
   +----------------------------------------------------------------------------------------------------------------+
   */
        Popup pop = new Popup();
        pop.getContent().add(hboxBarraPedido);
        tablaPedidos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Pedidos pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
                if (pedidoSeleccionado != null) {
                    pop.show(
                            tablaPedidos.getScene().getWindow(),
                            event.getSceneX(),
                            event.getSceneY()
                    );
                    hboxBarraPedido.setVisible(true);

                }
            }
        });
            /*
        +----------------------------------------------------------------------------------------------------------------+
        |                                 Filtro de busqueda por nombre,apellido y email                                 |
        +----------------------------------------------------------------------------------------------------------------+
        */

        FilteredList<Pedidos> filterPedidos = new FilteredList<Pedidos>(pedidos, b -> true);
        TextBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filterPedidos.setPredicate(pedido -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (String.valueOf(pedido.getId()).contains(newValue.toLowerCase())) {
                    return true;
                } else if (String.valueOf(pedido.getIdUsuario()).contains(newValue.toLowerCase())) {
                    return true;
                } else if (pedido.getFecha().contains(newValue.toLowerCase())) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Pedidos> sortedData = new SortedList<>(filterPedidos);
        sortedData.comparatorProperty().bind(tablaPedidos.comparatorProperty());
        tablaPedidos.setItems(sortedData);



        /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                      Movimiento de posición de las Vbox                                        |
   +----------------------------------------------------------------------------------------------------------------+
   */

        vboxModificarPedido.setOnMousePressed(event -> {
            xOffset = event.getSceneX() - vboxModificarPedido.getLayoutX();
            yOffset = event.getSceneY() - vboxModificarPedido.getLayoutY();
        });
        vboxModificarPedido.setOnMouseDragged(event -> {
            vboxModificarPedido.setLayoutX(event.getScreenX() - xOffset);
            vboxModificarPedido.setLayoutY(event.getScreenY() - yOffset);
        });


    }
         /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                      Exportar datos para las llamadas                                          |
   +----------------------------------------------------------------------------------------------------------------+
   */

    public void exportDataPedidos(Pedidos pedido) {
        textFieldIdPedidoJoin.setText(String.valueOf(pedido.getId()));
        textFieldIdUsuarioJoin.setText(String.valueOf(pedido.getIdUsuario()));
        textFieldIdProductoJoin.setText(String.valueOf(pedido.getIdProducto()));
        textFieldCantidadJoin.setText(String.valueOf(pedido.getCantidad()));
        textFieldFechaJoin.setText(String.valueOf(pedido.getFecha()));

    }

    public int exportDataId(Pedidos pedido) {
        return pedido.getId();
    }

           /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                        CRUD de la view de usuarios                                             |
   +----------------------------------------------------------------------------------------------------------------+
   */

    public void modificarPedido() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modificar el pedido");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea modificar el pedido?");

        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);


        alert.getButtonTypes().setAll(si, no);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == si) {
            PedidosController.actualizarPedido(
                    textFieldIdUsuarioJoin,
                    textFieldIdProductoJoin,
                    textFieldCantidadJoin,
                    textFieldIdPedidoJoin
            );
            tablaPedidos.setItems(FXCollections.observableArrayList(ModelPedidos.getPedidos()));
        }
    }


    public void eliminarPedido() {
        Pedidos pedido = tablaPedidos.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar pedido");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea eliminar el pedido?");

        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(si, no);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == si) {
            pedidosController.eliminarPedido(exportDataId(pedido));
            tablaPedidos.setItems(FXCollections.observableArrayList(ModelPedidos.getPedidos()));
        }
        hboxBarraPedido.setVisible(false);
    }

     /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                        Botones de la interfaz                                                  |
   +----------------------------------------------------------------------------------------------------------------+
   */

    public void alerts(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void atras(ActionEvent event) {
        mainView.abrirVentana("/View/Main.fxml", "Menu Principal");
        ((Stage) btnAtras.getScene().getWindow()).close();
    }

    public void exportXML(ActionEvent event) {
        ExportXML exportXML = new ExportXML();
        exportXML.ExportPedidos();
        alerts("Exportar XML", "Archivo XML exportado correctamente.");

    }


    public void modificarPedidosVista() {
        Pedidos pedido = tablaPedidos.getSelectionModel().getSelectedItem();
        exportDataPedidos(pedido);
        vboxModificarPedido.setVisible(true);
        hboxBarraPedido.setVisible(false);
    }

    public void cerrarVentana(ActionEvent event) {
        vboxModificarPedido.setVisible(false);
        hboxBarraPedido.setVisible(false);

    }


}
