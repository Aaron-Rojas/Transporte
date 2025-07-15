
package Modelo;

import java.util.Objects;

public class ActividadTuristica {
    private int ID_Actividad;
    private String NombreActividad;
    private String Descripcion;
    private String Duracion;
    private LugarTuristico lugarTuristico; 
    private Proveedor proveedor;         
    private boolean Estado; 

    
    public ActividadTuristica() {
        
    }

    public ActividadTuristica(int ID_Actividad, String NombreActividad, String Descripcion, String Duracion, LugarTuristico lugarTuristico, Proveedor proveedor, boolean Estado) {
        this.ID_Actividad = ID_Actividad;
        this.NombreActividad = NombreActividad;
        this.Descripcion = Descripcion;
        this.Duracion = Duracion;
        this.lugarTuristico = lugarTuristico;
        this.proveedor = proveedor;
        this.Estado = true;
    }

    public ActividadTuristica(String NombreActividad, String Descripcion, String Duracion, LugarTuristico lugarTuristico, Proveedor proveedor, boolean Estado) {
        this.NombreActividad = NombreActividad;
        this.Descripcion = Descripcion;
        this.Duracion = Duracion;
        this.lugarTuristico = lugarTuristico;
        this.proveedor = proveedor;
        this.Estado = Estado;
    }
    

    // Getters and Setters
    public int getID_Actividad() {
        return ID_Actividad;
    }

    public void setID_Actividad(int ID_Actividad) {
        this.ID_Actividad = ID_Actividad;
    }

    public String getNombreActividad() {
        return NombreActividad;
    }

    public void setNombreActividad(String NombreActividad) {
        this.NombreActividad = NombreActividad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String Duracion) {
        this.Duracion = Duracion;
    }

    public LugarTuristico getLugarTuristico() {
        return lugarTuristico;
    }

    public void setLugarTuristico(LugarTuristico lugarTuristico) {
        this.lugarTuristico = lugarTuristico;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public boolean isEstado() { // Convention for boolean getters is 'is'
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    @Override
    public String toString(){
        return NombreActividad ;
    }
    
    @Override
    public boolean equals(Object obj){
        if( this == obj) return true;
        if( obj == null || getClass() != obj.getClass()) return false;
        ActividadTuristica actividad = (ActividadTuristica) obj ;
        return ID_Actividad == actividad.ID_Actividad;
    }
    
    
    @Override
    public int hashCode(){
        return java.util.Objects.hash(ID_Actividad);
    }
}