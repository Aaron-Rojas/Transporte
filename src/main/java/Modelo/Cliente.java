
package Modelo;

public class Cliente {

    //Atributos de la tabla Clientes
    private int idCliente;
    private String dni;
    private String nombreCompleto; 
    private String telefono;
    private String email;
    private String preferencias;


    //constructor Vacio, para el DAO
    public Cliente() {
    }

    //constructor 
    public Cliente(int idCliente, String dni, String nombreCompleto, String telefono, String email, String preferencias) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
        this.preferencias = preferencias;
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
    
       
}    

