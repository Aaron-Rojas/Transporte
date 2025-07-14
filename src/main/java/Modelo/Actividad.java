package Modelo;

public class Actividad {

    private int idActividad;
    private String nombre;
    private String descripcion;
    private String duracion; // ¡CAMBIADO A STRING!
    private String estado;
    private int idLugarTuristico;
    private int idProveedor;

    // Constructor completo (para listar o editar)
    public Actividad(int idActividad, String nombre, String descripcion, String duracion, int idLugarTuristico, int idProveedor, String estado) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.idLugarTuristico = idLugarTuristico;
        this.idProveedor = idProveedor;
        this.estado = estado;
    }

    // Constructor sin ID (para insertar)
    public Actividad(String nombre, String descripcion, String duracion, String estado, int idLugarTuristico, int idProveedor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.idLugarTuristico = idLugarTuristico;
        this.idProveedor = idProveedor;
    }

    // Getters y Setters
    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() { // ¡CAMBIADO A STRING!
        return duracion;
    }

    public void setDuracion(String duracion) { // ¡CAMBIADO A STRING!
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdLugarTuristico() {
        return idLugarTuristico;
    }

    public void setIdLugarTuristico(int idLugarTuristico) {
        this.idLugarTuristico = idLugarTuristico;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
}