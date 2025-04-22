package Models;

import Connection.ConnectDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Class.*;

public class ModelProductos {

    public static List<Productos> getPedidos() {
        List<Productos> productos = new ArrayList<>();
        String sql = "SELECT id,nombre,precio,categoria_id FROM productos";
        try {
            if (ConnectDB.getConn() != null) {
                Statement stmt = ConnectDB.getStmt();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Productos producto = new Productos(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("categoria_id")
                    );
                    productos.add(producto);
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
