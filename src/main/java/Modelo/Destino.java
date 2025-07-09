package Modelo;

public class Destino {
    private int idDestino;
    private String nombreDestino;
    private String descripcion;
    private String estado; // <--- New field

    // Constructor vacío
    public Destino() {
    }

    // Constructor para crear un nuevo destino (sin ID, ya que es auto_increment)
    public Destino(String nombreDestino, String descripcion, String estado) { // <--- Added estado
        this.nombreDestino = nombreDestino;
        this.descripcion = descripcion;
        this.estado = estado; // <--- Added
    }

    // Constructor para recuperar un destino de la base de datos (con ID)
    public Destino(int idDestino, String nombreDestino, String descripcion, String estado) { // <--- Added estado
        this.idDestino = idDestino;
        this.nombreDestino = nombreDestino;
        this.descripcion = descripcion;
        this.estado = estado; // <--- Added
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

    public String getEstado() { // <--- New getter
        return estado;
    }

    public void setEstado(String estado) { // <--- New setter
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Destino{" +
               "idDestino=" + idDestino +
               ", nombreDestino='" + nombreDestino + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", estado='" + estado + '\'' + // <--- Added to toString
               '}';
    }
}


