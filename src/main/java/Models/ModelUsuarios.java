package Models;


import Class.*;
import Connection.ConnectDB;

import java.sql.ResultSet;
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


}
