package Controller;


import Models.ModelPedidos;
import Models.ModelUsuarios;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import Class.Usuarios;
import Class.Pedidos;


import java.util.List;
import java.util.Optional;

public class UsuariosController {

    private ModelUsuarios modelUsuarios = new ModelUsuarios();


        /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                        CRUD de la view de usuarios                                             |
   +----------------------------------------------------------------------------------------------------------------+
   */

    public void agregar(String nombre, String apellido, String email, VBox box) {

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
            alerts("Error", "Por favor, complete todos los campos.");
        } else {
            modelUsuarios.agregarUsuario(nombre, apellido, email);
            box.setVisible(false);
        }
    }

    public void modificar(String nombre, String apellido, String email, int id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modificar el usuario");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea modificar el usuario?");

        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(si, no);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == si) {
            modelUsuarios.actualizarUsuario(
                    nombre,
                    apellido,
                    email,
                    id
            );

        }
    }

    public void eliminar(int id) {
        List<Pedidos> pedidosList = ModelPedidos.getPedidos();
        Boolean usuarioEncontrado = false;
        for (Pedidos pedidos : pedidosList) {
            if (pedidos.getIdUsuario() == id) {
                usuarioEncontrado = true;
            }
        }

        if (usuarioEncontrado) {
            alerts("Error", "No se puede eliminar el usuario porque tiene pedidos asociados.");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar el usuario");
            alert.setHeaderText(null);
            alert.setContentText("¿Está seguro de que desea eliminar el usuario?");

            ButtonType si = new ButtonType("Sí");
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(si, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == si) {
                modelUsuarios.eliminarUsuario(id);
            }
        }
    }

    public void alerts(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
