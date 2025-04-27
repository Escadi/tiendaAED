package Views;

import Class.*;
import Connection.ConnectDB;
import Functions.ExportCSV;
import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class UsuariosView {
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private TextField TextBuscar;
    @FXML
    private Button btnModificar;
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
    private TextField TextNombre;
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
        ColidUsuario.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ObservableList<Usuarios> usuario = FXCollections.observableArrayList(ModelUsuarios.getUsuarios());
        TablaUsuarios.setItems(usuario);

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

        vboxAgregar.setOnMousePressed(event -> {
            xOffset = event.getSceneX() - vboxAgregar.getLayoutX();
            yOffset = event.getSceneY() - vboxAgregar.getLayoutY();
        });
        vboxAgregar.setOnMouseDragged(event -> {
            vboxAgregar.setLayoutX(event.getScreenX() - xOffset);
            vboxAgregar.setLayoutY(event.getScreenY() - yOffset);
        });
        vboxModificar.setOnMousePressed(event -> {
            xOffset = event.getSceneX() - vboxModificar.getLayoutX();
            yOffset = event.getSceneY() - vboxModificar.getLayoutY();
        });
        vboxModificar.setOnMouseDragged(event -> {
            vboxModificar.setLayoutX(event.getScreenX() - xOffset);
            vboxModificar.setLayoutY(event.getScreenY() - yOffset);
        });







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

    public void modificarUsuario(ActionEvent event) {
        vboxModificar.setVisible(true);
        hboxMod.setVisible(false);
    }
    public void agregarUsuario(ActionEvent event) {
        vboxAgregar.setVisible(true);
    }

    public void cerrarVentana(ActionEvent event) {
        vboxAgregar.setVisible(false);
        vboxModificar.setVisible(false);
    }
    public void createCSV(ActionEvent event) {
        ExportCSV exportCSV= new ExportCSV();
        exportCSV.ExportUsuarios();
    }


}
