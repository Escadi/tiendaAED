package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Class.*;
import Connection.ConnectDB;

public class ModelPedidos {

    public static List<Pedidos> getPedidos() {
        List<Pedidos> pedidos = new ArrayList<>();
        String sql = "SELECT id,usuario_id,producto_id,cantidad,fecha FROM pedidos";
        try {
            if (ConnectDB.getConn() != null) {
                Statement stmt = ConnectDB.getStmt();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Pedidos pedido = new Pedidos(
                            rs.getInt("id"),
                            rs.getInt("usuario_id"),
                            rs.getInt("producto_id"),
                            rs.getInt("cantidad"),
                            rs.getString("fecha")
                    );
                    pedidos.add(pedido);
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
}
