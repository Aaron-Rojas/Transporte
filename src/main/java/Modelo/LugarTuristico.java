package Modelo;

import java.util.Objects;

public class LugarTuristico {
    private int idLugarTuristico;
    private String nombreLugar;
    private String tipoLugar;
    private String descripcion;
    private Destino destino;
    private Proveedor proveedor;
    private boolean estado;

    public LugarTuristico() {
    }

    
    public LugarTuristico(int idLugarTuristico, String nombreLugar, String tipoLugar, String descripcion, Destino destino, Proveedor proveedor, boolean estado) {
        this.idLugarTuristico = idLugarTuristico;
        this.nombreLugar = nombreLugar;
        this.tipoLugar = tipoLugar;
        this.descripcion = descripcion;
        this.destino = destino;
        this.proveedor = proveedor;
        this.estado = true;
    }

    public LugarTuristico(String nombreLugar, String tipoLugar, String descripcion, Destino destino, Proveedor proveedor, boolean estado) {
        this.nombreLugar = nombreLugar;
        this.tipoLugar = tipoLugar;
        this.descripcion = descripcion;
        this.destino = destino;
        this.proveedor = proveedor;
        this.estado = estado;
    }

    public int getIdLugarTuristico() {
        return idLugarTuristico;
    }

    public void setIdLugarTuristico(int idLugarTuristico) {
        this.idLugarTuristico = idLugarTuristico;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


   @Override
    public String toString() {
        return nombreLugar; // Muestra solo el nombre en el ComboBox, es lo más común
                                 // Original: return "Proveedor{" + "idProveedor=" + idProveedor + ", nombreProveedor='" + nombreProveedor + '\'' + ", contacto='" + contacto + '\'' + ", estado='" + Estado + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LugarTuristico lugarTuristico = (LugarTuristico) o;
        return idLugarTuristico == lugarTuristico.idLugarTuristico; // La igualdad se basa en el ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLugarTuristico); // El hash se basa en el ID
    }

    
}
