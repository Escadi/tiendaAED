package Class;

public class Productos {
    private int id;
    private String nombre;
    private double precio;
    private int idCategoria;

    public Productos(int id, String nombre, double precio, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
