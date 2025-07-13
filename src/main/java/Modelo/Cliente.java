
package Modelo;

public class Cliente {

    //Atributos de la tabla Clientes
    private int idCliente;
    private String dni;
    private String nombreCompleto; 
    private String telefono;
    private String email;
    private String preferencias;
    private boolean Estado;

    //constructor Vacio, para el DAO
    public Cliente() {
    }

    //constructor 
    public Cliente(String dni, String nombreCompleto, String telefono, String email, String preferencias, boolean activo) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
        this.preferencias = preferencias;
        this.Estado= Estado;
    }
    
    public Cliente(String dni, String nombreCompleto, String telefono, String email, String preferencias) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
        this.preferencias = preferencias;
        this.Estado = true; // Por defecto, un nuevo cliente está activo
    }

    //getter and setter
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public boolean isActivo() {
        return Estado;
    }

    public void setActivo(boolean Estado) {
        this.Estado = Estado;
    }
    
    @Override
    public String toString() {
        // Esto es lo que se mostrará en el JComboBox
        // Puedes personalizarlo para mostrar más información si lo deseas
        return nombreCompleto + " Cliente seleccionado (DNI: " + dni + ")";
    }
       
}    

