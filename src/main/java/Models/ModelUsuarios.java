package Models;


import Class.*;
import Connection.ConnectDB;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelUsuarios {

    public static List<Usuarios> getUsuarios() {
        ConnectDB.openConn();
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido ,email FROM Usuarios";
        try {
            if (ConnectDB.getConn() != null) {
                Statement stmt = ConnectDB.getStmt();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Usuarios usuario = new Usuarios(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email")
                    );
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {

        }
        return usuarios;
    }
    public int idUsuario() {
        int id = 0;
        String sql = "SELECT MAX(id) FROM usuarios";
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

    public void agregarUsuario(String nombre, String apellido, String email) {

        String sql = "INSERT INTO usuarios (id, nombre, apellido, email) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = ConnectDB.getConn().prepareStatement(sql);
            stmt.setInt(1, idUsuario());
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setString(4, email);
            int execute = stmt.executeUpdate();
            if (execute > 0) {
                alerts("Usuario agregado", "El usuario ha sido agregado correctamente");
            } else {
                alerts("Error", "No se pudo agregar el usuario");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarUsuario(String nombre, String apellido, String email, int id) {
        String sql = "UPDATE usuarios SET nombre = ?, apellido =?, email = ?  WHERE id = ?";
        try {
            PreparedStatement stmt = ConnectDB.getConn().prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, email);
            stmt.setInt(4, id);
            int execute = stmt.executeUpdate();
            if (execute > 0) {
                alerts("Usuario actualizado", "El usuario " + id + " ha sido actualizado correctamente");
            } else {
                alerts("Error", "No se pudo actualizar el usuario");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void eliminarUsuario(int id) {
        String url = "DELETE FROM usuarios WHERE id = ?";
        try {
            PreparedStatement stmt = ConnectDB.getConn().prepareStatement(url);
            stmt.setInt(1, id);
            int execute = stmt.executeUpdate();
            if (execute > 0) {
                alerts("Usuario eliminado", "El usuario " + id + "ha sido eliminado correctamente");
            } else {
                alerts("Error", "No se pudo eliminar el usuario");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
