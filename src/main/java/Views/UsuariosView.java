package Views;

import Class.*;

import Controller.*;
import Functions.ExportCSV;
import Models.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;


public class UsuariosView {
    private UsuariosController usuariosController = new UsuariosController();
    private PedidosController pedidosController = new PedidosController();
    private MainView mainView = new MainView();
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TextField TextBuscar;
    @FXML
    private Button btnModificarU;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnAtras;
    @FXML
    private VBox vboxModificar;
    @FXML
    private HBox hboxMod;
    @FXML
    private VBox vboxAgregar;
    @FXML
    private VBox vboxPedidos;
    @FXML
    private TextField textFieldNombreJoin;
    @FXML
    private TextField textFieldEmailJoin;
    @FXML
    private TextField textFieldApellidoJoin;
    @FXML
    private TextField textFieldModId;
    @FXML
    private TextField textFieldModNombre;
    @FXML
    private TextField textFieldModApellido;
    @FXML
    private TextField textFieldModEmail;
    @FXML
    private TextField textFieldModIdPedido;
    @FXML
    private TextField textFieldModCantidad;
    @FXML
    private ComboBox<String> comboProducto;
    @FXML
    private Button btnAgregarPedido;
    @FXML
    private TableView<Usuarios> TablaUsuarios;
    @FXML
    private TableColumn<Usuarios, Integer> ColidUsuario;
    @FXML
    private TableColumn<Usuarios, String> ColNombre;
    @FXML
    private TableColumn<Usuarios, String> ColApellido;
    @FXML
    private TableColumn<Usuarios, String> ColEmail;


    public void initialize() {
        llenarComboBox();
        ColidUsuario.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ObservableList<Usuarios> usuario = FXCollections.observableArrayList(ModelUsuarios.getUsuarios());
        TablaUsuarios.setItems(usuario);

        /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                     Posicionamiento de los botones                                             |
   +----------------------------------------------------------------------------------------------------------------+
   */
        Popup pop = new Popup();
        pop.getContent().add(hboxMod);
        TablaUsuarios.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Usuarios usuarios = TablaUsuarios.getSelectionModel().getSelectedItem();
                if (usuarios != null) {
                    pop.show(
                            TablaUsuarios.getScene().getWindow(),
                            event.getSceneX(),
                            event.getSceneY()
                    );
                    hboxMod.setVisible(true);

                }
            }
        });

           /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                 Filtro de busqueda por nombre,apellido y email                                 |
   +----------------------------------------------------------------------------------------------------------------+
   */

        FilteredList<Usuarios> filterUsuarios = new FilteredList<Usuarios>(usuario, b -> true);
        TextBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filterUsuarios.setPredicate(usuarios -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                } else if (usuarios.getNombre().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (usuarios.getApellido().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                } else if (usuarios.getEmail().toLowerCase().contains(newValue.toLowerCase())) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Usuarios> sortedData = new SortedList<>(filterUsuarios);
        sortedData.comparatorProperty().bind(TablaUsuarios.comparatorProperty());
        TablaUsuarios.setItems(sortedData);

        /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                      Movimiento de posición de las Vbox                                        |
   +----------------------------------------------------------------------------------------------------------------+
   */

        //Movimiento de ventanas agregar usuarios
        vboxAgregar.setOnMousePressed(event -> {
            xOffset = event.getSceneX() - vboxAgregar.getLayoutX();
            yOffset = event.getSceneY() - vboxAgregar.getLayoutY();
        });
        vboxAgregar.setOnMouseDragged(event -> {
            vboxAgregar.setLayoutX(event.getScreenX() - xOffset);
            vboxAgregar.setLayoutY(event.getScreenY() - yOffset);
        });

        //Movimiento de ventanas modificar usuarios
        vboxModificar.setOnMousePressed(event -> {
            xOffset = event.getSceneX() - vboxModificar.getLayoutX();
            yOffset = event.getSceneY() - vboxModificar.getLayoutY();
        });
        vboxModificar.setOnMouseDragged(event -> {
            vboxModificar.setLayoutX(event.getScreenX() - xOffset);
            vboxModificar.setLayoutY(event.getScreenY() - yOffset);
        });

        //Movimiento de ventanas pedidos
        vboxPedidos.setOnMousePressed(event -> {
            xOffset = event.getSceneX() - vboxPedidos.getLayoutX();
            yOffset = event.getSceneY() - vboxPedidos.getLayoutY();
        });
        vboxPedidos.setOnMouseDragged(event -> {
            vboxPedidos.setLayoutX(event.getScreenX() - xOffset);
            vboxPedidos.setLayoutY(event.getScreenY() - yOffset);
        });
    }

        /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                      Exportar datos para las llamadas                                          |
   +----------------------------------------------------------------------------------------------------------------+
   */

    public void exportData(Usuarios usuario) {
        textFieldModNombre.setText(usuario.getNombre());
        textFieldModApellido.setText(usuario.getApellido());
        textFieldModEmail.setText(usuario.getEmail());
        textFieldModId.setText(String.valueOf(usuario.getId()));
        vboxModificar.setVisible(true);
        hboxMod.setVisible(false);
    }

    public void exportDataPedidos(Usuarios usuario) {
        textFieldModIdPedido.setText(String.valueOf(usuario.getId()));
    }

    public int exportIdUsuario(Usuarios usuario) {
        int id = usuario.getId();
        return id;
    }


        /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                        CRUD de la view de usuarios                                             |
   +----------------------------------------------------------------------------------------------------------------+
   */

    public void agregar() {
        String nombre = textFieldNombreJoin.getText();
        String apellido = textFieldApellidoJoin.getText();
        String email = textFieldEmailJoin.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
            alerts("Error", "Por favor, complete todos los campos.");
        } else {
            usuariosController.agregarUsuario(nombre, apellido, email);
            TablaUsuarios.setItems(FXCollections.observableArrayList(ModelUsuarios.getUsuarios()));
            vboxAgregar.setVisible(false);
        }
    }

    public void modificar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modificar el usuario");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea modificar el usuario?");

        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(si, no);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == si) {
            usuariosController.actualizarUsuario(
                    textFieldModNombre,
                    textFieldModApellido,
                    textFieldModEmail,
                    textFieldModId
            );
            TablaUsuarios.setItems(FXCollections.observableArrayList(ModelUsuarios.getUsuarios()));
        }
    }

    public void eliminar() {
        Usuarios usuario = TablaUsuarios.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar el usuario");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea eliminar el usuario?");

        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(si, no);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == si) {
            usuariosController.eliminarUsuario(exportIdUsuario(usuario));
            TablaUsuarios.setItems(FXCollections.observableArrayList(ModelUsuarios.getUsuarios()));
        }
    }







    /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                        Botones de la interfaz                                                  |
   +----------------------------------------------------------------------------------------------------------------+
   */

    public void llenarComboBox() {
        List<Productos> listaProducto = ModelProductos.getProductos();
        ObservableList<String> lista = FXCollections.observableArrayList();
        for (Productos producto : listaProducto) {
            lista.add(producto.getNombre());
        }
        comboProducto.setItems(lista);
    }


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

    public void modificarUsuario(ActionEvent event) {
        Usuarios usuarios = TablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarios != null) {
            exportData(usuarios);
        } else {
            alerts("Error", "Por favor, seleccione un usuario.");
        }

    }

    public void agregarUsuario(ActionEvent event) {
        vboxAgregar.setVisible(true);
    }

    public void pedidosAgregar(ActionEvent event) {
        Usuarios usuarios = TablaUsuarios.getSelectionModel().getSelectedItem();
        exportDataPedidos(usuarios);
        vboxPedidos.setVisible(true);
    }
    public void realizarPedidos(ActionEvent event) {
        pedidosController.agregarPedido(
                textFieldModIdPedido,
                comboProducto,
                textFieldModCantidad
        );
    }

    public void cerrarVentana(ActionEvent event) {
        vboxAgregar.setVisible(false);
        vboxModificar.setVisible(false);
        vboxPedidos.setVisible(false);
    }

    public void agregarUsuarios(ActionEvent event) {
        agregar();
    }

    public void createCSV(ActionEvent event) {
        ExportCSV exportCSV = new ExportCSV();
        exportCSV.ExportUsuarios();
    }


}