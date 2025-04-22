package Class;

public class Direcciones {
    private int idUsuario;
    private String calle;
    private String ciudad;
    private String codigo_postal;

    public Direcciones(int idUsuario, String calle, String ciudad, String codigo_postal) {
        this.idUsuario = idUsuario;
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigo_postal = codigo_postal;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    @Override
    public String toString() {
        return "Direcciones{" +
                "idUsuario=" + idUsuario +
                ", calle='" + calle + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", codigo_postal='" + codigo_postal + '\'' +
                '}';
    }
}
