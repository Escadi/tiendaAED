package Controller;

import Models.ModelPedidos;
import javafx.collections.FXCollections;
import javafx.scene.control.*;

import java.util.Optional;

public class PedidosController {

    private ModelPedidos modelPedidos = new ModelPedidos();

             /*
   +----------------------------------------------------------------------------------------------------------------+
   |                                        CRUD de la view de usuarios                                             |
   +----------------------------------------------------------------------------------------------------------------+
   */

    public void modificarPedido(TextField idUsuario, TextField idProducto, TextField cantidad, TextField idPedido, TableView tablaPedidos) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modificar el pedido");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea modificar el pedido?");

        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);


        alert.getButtonTypes().setAll(si, no);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == si) {
            ModelPedidos.actualizarPedido(
                    idUsuario,
                    idProducto,
                    cantidad,
                    idPedido
            );
            tablaPedidos.setItems(FXCollections.observableArrayList(ModelPedidos.getPedidos()));
        }
    }


    public void eliminarPedido(TableView tablaPedidos, int id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar pedido");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea eliminar el pedido?");

        ButtonType si = new ButtonType("Sí");
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(si, no);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == si) {
            modelPedidos.eliminarPedido(id);
            tablaPedidos.setItems(FXCollections.observableArrayList(ModelPedidos.getPedidos()));
        }

    }


}
