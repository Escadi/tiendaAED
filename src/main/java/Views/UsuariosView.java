package Views;

import Class.*;
import Connection.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import Models.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class UsuariosView {
    @FXML
    private TextField TextBuscar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAgregar;
    @FXML
    private HBox hboxMod;
    @FXML
    private VBox vboxAgregar;
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
        ConnectDB.openConn();
        ColidUsuario.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ObservableList<Usuarios> usuario = FXCollections.observableArrayList(ModelUsuarios.getUsuarios());
        TablaUsuarios.setItems(usuario);

        TablaUsuarios.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                Usuarios usuarioSeleccionado = TablaUsuarios.getSelectionModel().getSelectedItem();
                if (usuarioSeleccionado != null) {
                    hboxMod.setVisible(true);
                }
            }
        });
    }







    public void agregarUsuario(ActionEvent event) {
        vboxAgregar.setVisible(true);
    }
    public void cerrarVentana(ActionEvent event) {
        vboxAgregar.setVisible(false);
    }


}
