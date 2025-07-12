package Modelo; // Tu paquete existente

public class Proveedor {
    private int idProveedor;
    private String nombreProveedor;
    private String contacto;
    private String estado; // Nuevo atributo para el estado

    // Constructor vacío
    public Proveedor() {
    }
    
    // Constructor con todos los campos (incluyendo el nuevo estado)
    public Proveedor(int idProveedor, String nombreProveedor, String contacto, String estado) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.contacto = contacto;
        this.estado = estado;
    }

    // Constructor sin ID (útil para crear nuevos proveedores), ahora con estado
    public Proveedor(String nombreProveedor, String contacto, String estado) {
        this.nombreProveedor = nombreProveedor;
        this.contacto = contacto;
        this.estado = estado;
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

    // Nuevo Getter y Setter para el estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
               "idProveedor=" + idProveedor +
               ", nombreProveedor='" + nombreProveedor + '\'' +
               ", contacto='" + contacto + '\'' +
               ", estado='" + estado + '\'' + // Incluir estado en toString
               '}';
    }
}