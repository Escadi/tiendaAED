package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {
    private static Connection conn = null;
    private static final String URL = "jdbc:mysql://localhost";
    private static final String PORT = "3306";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE = "tienda";

    private static Statement stmt;

    public static void openConn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = URL + ":" + PORT + "/" + DATABASE;
        try {
            conn = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static Connection getConn() {
        return conn;
    }
    public static void closeConn() {
        try {
            System.out.println("Closing connection");
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Statement getStmt() {
        return stmt;
    }




}
