
package Modelo;

public class ProveedorTipoServicio {
    private int idProveedorTipoServicio;
    private int idProveedor;
    private int idTipoServicio;

    // Constructor vacío
    public ProveedorTipoServicio() {
    }

    // Constructor con todos los campos
    public ProveedorTipoServicio(int idProveedorTipoServicio, int idProveedor, int idTipoServicio) {
        this.idProveedorTipoServicio = idProveedorTipoServicio;
        this.idProveedor = idProveedor;
        this.idTipoServicio = idTipoServicio;
    }

    // Constructor sin el ID autoincremental (para inserciones)
    public ProveedorTipoServicio(int idProveedor, int idTipoServicio) {
        this.idProveedor = idProveedor;
        this.idTipoServicio = idTipoServicio;
    }

    // Getters y Setters
    public int getIdProveedorTipoServicio() {
        return idProveedorTipoServicio;
    }

    public void setIdProveedorTipoServicio(int idProveedorTipoServicio) {
        this.idProveedorTipoServicio = idProveedorTipoServicio;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    
    @Override
    public String toString() {
        return "ProveedorTipoServicio{" +
                "idProveedorTipoServicio=" + idProveedorTipoServicio +
                ", idProveedor=" + idProveedor +
                ", idTipoServicio=" + idTipoServicio +
                '}';
    }
}
