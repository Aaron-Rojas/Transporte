package Modelo;

public class ProveedorTipoServicio {
    private int idProveedorTipoServicio;
    private Proveedor proveedor;  // Objeto Proveedor en lugar de solo ID
    private TipoServicio tipoServicio;  // Objeto TipoServicio en lugar de solo ID

   
    public ProveedorTipoServicio() {
    }

    
    public ProveedorTipoServicio(int idProveedorTipoServicio, Proveedor proveedor, TipoServicio tipoServicio) {
        this.idProveedorTipoServicio = idProveedorTipoServicio;
        this.proveedor = proveedor;
        this.tipoServicio = tipoServicio;
    }

    
    public ProveedorTipoServicio(Proveedor proveedor, TipoServicio tipoServicio) {
        this.proveedor = proveedor;
        this.tipoServicio = tipoServicio;
    }

    
    public int getIdProveedorTipoServicio() {
        return idProveedorTipoServicio;
    }

    public void setIdProveedorTipoServicio(int idProveedorTipoServicio) {
        this.idProveedorTipoServicio = idProveedorTipoServicio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    
    public int getIdProveedor() {
        return proveedor != null ? proveedor.getIdProveedor() : 0;
    }

    public int getIdTipoServicio() {
        return tipoServicio != null ? tipoServicio.getIdTipoServicio() : 0;
    }

    @Override
    public String toString() {
        return "ProveedorTipoServicio{" +
                "idProveedorTipoServicio=" + idProveedorTipoServicio +
                ", proveedor=" + (proveedor != null ? proveedor.getNombreProveedor() : "null") +
                ", tipoServicio=" + (tipoServicio != null ? tipoServicio.getNombreTipo() : "null") +
                '}';
    }
}