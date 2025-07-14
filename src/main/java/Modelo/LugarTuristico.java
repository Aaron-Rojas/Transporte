package Modelo;

public class LugarTuristico {
    private int idLugarTuristico;
    private String nombre;
    private String tipo;
    private String descripcion;
    private int idDestino;
    private int idProveedor;
    private String estado;

    // Constructor para combos: solo ID y nombre
   public LugarTuristico(int idLugarTuristico, String nombre) {
       this.idLugarTuristico = idLugarTuristico;
       this.nombre = nombre;
   }


    public LugarTuristico(int idLugarTuristico, String nombre, String tipo, String descripcion, int idDestino, int idProveedor, String estado) {
        this.idLugarTuristico = idLugarTuristico;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.idDestino = idDestino;
        this.idProveedor = idProveedor;
        this.estado = estado;
    }

    public int getIdLugarTuristico() { return idLugarTuristico; }
    public void setIdLugarTuristico(int idLugarTuristico) { this.idLugarTuristico = idLugarTuristico; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getIdDestino() { return idDestino; }
    public void setIdDestino(int idDestino) { this.idDestino = idDestino; }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return nombre; // o getNombre() si así lo tienes
    }

}
