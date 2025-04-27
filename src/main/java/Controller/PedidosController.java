package Controller;

import Connection.ConnectDB;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PedidosController {


    public int idPedido() {
        int id = 0;
        String sql = "SELECT MAX(id) FROM pedidos";
        try {
            PreparedStatement stmt = ConnectDB.getConn().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id + 1;
    }

    ;

    public int idProducto(String nombre) {
        String url = "SELECT id FROM productos WHERE nombre = ?";
        int id = 0;
        try {
            PreparedStatement stmt = ConnectDB.getConn().prepareStatement(url);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;

    }

    public void agregarPedido(TextField usuarioID, ComboBox productoID, TextField cantidad) {

        String sql = "INSERT INTO pedidos (id, usuario_id, producto_id, cantidad, fecha) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = ConnectDB.getConn().prepareStatement(sql);
            stmt.setInt(1, idPedido());
            stmt.setInt(2, Integer.parseInt((usuarioID.getText())));
            stmt.setInt(3, idProducto(productoID.getSelectionModel().getSelectedItem().toString()));
            stmt.setInt(4, Integer.parseInt(cantidad.getText()));
            stmt.setString(5, LocalDate.now().toString());
            int execute = stmt.executeUpdate();
            if (execute > 0) {
                alerts("Pedido generado", "El pedido ha generado correctamente");
            } else {
                alerts("Error", "No se pudo generar el pedido");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void actualizarPedido(TextField idUsuario, TextField idProducto, TextField cantidad, TextField id) {
        String sql = "UPDATE pedidos SET usuario_id = ?, producto_id =?, cantidad = ?  WHERE id = ?";
        try {
            PreparedStatement stmt = ConnectDB.getConn().prepareStatement(sql);
            stmt.setString(1, idUsuario.getText());
            stmt.setString(2, idProducto.getText());
            stmt.setString(3, cantidad.getText());
            stmt.setString(4, id.getText());
            int execute = stmt.executeUpdate();
            if (execute > 0) {
                alerts("Pedido Actualizado", "El pedido " + id.getText() + " ha sido actualizado correctamente");
            } else {
                alerts("Error", "No se pudo actualizar el pedido");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarPedido(int id) {
        String url = "DELETE FROM pedidos WHERE id = ?";
        try {
            PreparedStatement stmt = ConnectDB.getConn().prepareStatement(url);
            stmt.setInt(1, id);
            int execute = stmt.executeUpdate();
            if (execute > 0) {
                alerts("Pedido eliminado", "El pedido " + id + "ha sido eliminado correctamente");
            } else {
                alerts("Error", "No se pudo eliminar el pedido");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static void alerts(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
