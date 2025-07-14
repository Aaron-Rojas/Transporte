package Modelo;

/**
 * Clase que representa un Hotel en el sistema de gestión de viajes.
 */
public class Hotel {

    private int idHotel;
    private int idDestino;
    private int idProveedor;
    private String nombreHotel;
    private String descripcion;
    private String direccion;
    private String categoria;
    private String estado;

    // ─────────────────────────────────────────────────────────────
    // Constructores
    // ─────────────────────────────────────────────────────────────

    /**
     * Constructor reducido (usado para mostrar en tablas).
     * @param idHotel ID del hotel.
     * @param nombreHotel Nombre del hotel.
     * @param descripcion Descripción del hotel.
     */
    public Hotel(int idHotel, String nombreHotel, String descripcion) {
        this.idHotel = idHotel;
        this.nombreHotel = nombreHotel;
        this.descripcion = descripcion;
    }

    /**
     * Constructor para registrar un nuevo hotel (sin ID).
     * @param nombreHotel Nombre del hotel.
     * @param descripcion Descripción del hotel.
     * @param direccion Dirección del hotel.
     * @param categoria Categoría (Ej. 3 Estrellas).
     * @param estado Estado del hotel (Ej. Activo, Inactivo).
     * @param idDestino ID del destino asociado.
     * @param idProveedor ID del proveedor asociado.
     */
    public Hotel(String nombreHotel, String descripcion, String direccion, String categoria, String estado, int idDestino, int idProveedor) {
        this.nombreHotel = nombreHotel;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.categoria = categoria;
        this.estado = estado;
        this.idDestino = idDestino;
        this.idProveedor = idProveedor;
    }

    /**
     * Constructor completo con ID (usado para actualización o lectura de base de datos).
     * @param idHotel ID del hotel.
     * @param nombreHotel Nombre del hotel.
     * @param descripcion Descripción del hotel.
     * @param direccion Dirección del hotel.
     * @param categoria Categoría del hotel.
     * @param estado Estado del hotel.
     * @param idDestino ID del destino asociado.
     * @param idProveedor ID del proveedor asociado.
     */
    public Hotel(int idHotel, String nombreHotel, String descripcion, String direccion, String categoria, String estado, int idDestino, int idProveedor) {
        this.idHotel = idHotel;
        this.nombreHotel = nombreHotel;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.categoria = categoria;
        this.estado = estado;
        this.idDestino = idDestino;
        this.idProveedor = idProveedor;
    }

    // ─────────────────────────────────────────────────────────────
    // Getters y Setters
    // ─────────────────────────────────────────────────────────────

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
