
package Modelo;


public class TipoServicio {
    private int idTipoServicio;
    private String nombreTipo;
    private String descripcion;

    
    public TipoServicio() {
    }

    
    public TipoServicio(int idTipoServicio, String nombreTipo, String descripcion) {
        this.idTipoServicio = idTipoServicio;
        this.nombreTipo = nombreTipo;
        this.descripcion = descripcion;
    }

   
    public int getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   
    @Override
    public String toString() {
        return "TipoServicio{" +
                "idTipoServicio=" + idTipoServicio +
                ", nombreTipo='" + nombreTipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}