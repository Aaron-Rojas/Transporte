package Modelo;

public class Destino {
    private int idDestino;
    private String nombreDestino;
    private String descripcion;
    private boolean estado; // Changed from String to boolean

    // Constructors
    public Destino() {
    }

    public Destino(String nombreDestino, String descripcion, boolean estado) { // Updated parameter
        this.nombreDestino = nombreDestino;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Destino(int idDestino, String nombreDestino, String descripcion, boolean estado) { // Updated parameter
        this.idDestino = idDestino;
        this.nombreDestino = nombreDestino;
        this.descripcion = descripcion;
        this.estado = true;
    }

    // Getters y Setters
    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() { // Changed from getEstado() to isEstado() for boolean
        return estado;
    }

    public void setEstado(boolean estado) { // Updated parameter
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Destino{" +
               "idDestino=" + idDestino +
               ", nombreDestino='" + nombreDestino + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", estado=" + estado + // Updated to reflect boolean
               '}';
    }
}