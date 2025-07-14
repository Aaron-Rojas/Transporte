package Modelo;

public class Destino {
    private int idDestino;
    private String nombreDestino;
    private String descripcion;
    private String estado; // Mantenemos String para mayor flexibilidad

    // Constructores
    public Destino() {
    }

    public Destino(String nombreDestino, String descripcion, String estado) {
        this.nombreDestino = nombreDestino;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Destino(int idDestino, String nombreDestino, String descripcion, String estado) {
        this.idDestino = idDestino;
        this.nombreDestino = nombreDestino;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Destino{" +
               "idDestino=" + idDestino +
               ", nombreDestino='" + nombreDestino + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", estado='" + estado + '\'' +
               '}';
    }
}


