package Modelo;

public class Usuario {
    private int idUsuario;
    private String nombreCompleto; // Este campo es el que se usa para el login y para mostrar el nombre
    private String correo;
    private String contraseña;
    private String estado;
    private int idRol;

    // Objeto Rol para facilitar el acceso a NombreRol en la interfaz
    private Rol rol;

    public Usuario() {
    }

    // Constructor para la creación y recuperación de datos
    public Usuario(int idUsuario, String nombreCompleto, String correo, String contraseña, String estado, int idRol, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contraseña = contraseña;
        this.estado = estado;
        this.idRol = idRol;
        this.rol=rol;
    }
    
    
    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getNombreCompleto() { return nombreCompleto; } 
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
}