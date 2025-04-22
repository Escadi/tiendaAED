package Class;

public class Pedidos {
    private int id;
    private int idUsuario;
    private int idProducto;
    private String fecha;
    private int cantidad;

    public Pedidos(int id, int idUsuario, int idProducto, int cantidad, String fecha) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idProducto=" + idProducto +
                ", fecha='" + fecha + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
