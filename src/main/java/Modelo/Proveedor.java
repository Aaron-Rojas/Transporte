package Modelo; 

import java.util.Objects;

public class Proveedor {
    private int idProveedor;
    private String nombreProveedor;
    private String contacto;
    private boolean Estado; // Nuevo atributo para el estado

    // Constructor vacío
    public Proveedor() {
    }
    
    // Constructor con todos los campos (incluyendo el nuevo estado)
    public Proveedor(int idProveedor, String nombreProveedor, String contacto, boolean estado) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.contacto = contacto;
        this.Estado = estado;
    }

    // Constructor sin ID (útil para crear nuevos proveedores), ahora con estado
    public Proveedor(String nombreProveedor, String contacto) {
        this.nombreProveedor = nombreProveedor;
        this.contacto = contacto;
        this.Estado = true;
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
    public boolean IsActivo() {
        return Estado;
    }

    public void setActivo(boolean Estado) {
        this.Estado = Estado;
    }

   
        
    @Override
    public String toString() {
        return nombreProveedor; // Muestra solo el nombre en el ComboBox, es lo más común
                                 // Original: return "Proveedor{" + "idProveedor=" + idProveedor + ", nombreProveedor='" + nombreProveedor + '\'' + ", contacto='" + contacto + '\'' + ", estado='" + Estado + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return idProveedor == proveedor.idProveedor; // La igualdad se basa en el ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor); // El hash se basa en el ID
    }
}