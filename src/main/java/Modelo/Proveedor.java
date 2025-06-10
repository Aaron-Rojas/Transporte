package Modelo;

public class Proveedor {
    private int idProveedor;
    private String nombreProveedor;
    private String contacto;

    // Constructor vacío
    public Proveedor() {
    }

    // Constructor con todos los campos
    public Proveedor(int idProveedor, String nombreProveedor, String contacto) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.contacto = contacto;
    }

    // Constructor sin ID (útil para crear nuevos proveedores)
    public Proveedor(String nombreProveedor, String contacto) {
        this.nombreProveedor = nombreProveedor;
        this.contacto = contacto;
    }

    // Getters y Setters
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
               "idProveedor=" + idProveedor +
               ", nombreProveedor='" + nombreProveedor + '\'' +
               ", contacto='" + contacto + '\'' +
               '}';
    }
}