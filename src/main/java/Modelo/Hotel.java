package Modelo;

import java.util.Arrays;

public class Hotel {
    
    private int idHotel;
    private String nombreHotel;
    private String direccion;
    private String categoria;
    private Destino destino;
    private Proveedor proveedor;
    private boolean estado;
    
    public static final String[] CATEGORIAS_VALIDAS = {
        "1 estrella", 
        "2 estrellas", 
        "3 estrellas", 
        "4 estrellas", 
        "5 estrellas"
    };  
    
    public void setCategoria(String categoria) {
        if (!Arrays.asList(CATEGORIAS_VALIDAS).contains(categoria)) {
            throw new IllegalArgumentException("Categoría no válida");
        }
        this.categoria = categoria;
    }

    public Hotel() {
    }

    public Hotel(String nombreHotel, String direccion, String categoria, Destino destino, Proveedor proveedor, boolean estado) {
        this.nombreHotel = nombreHotel;
        this.direccion = direccion;
        this.categoria = categoria;
        this.destino = destino;
        this.proveedor = proveedor;
        this.estado = estado;
    }

    public Hotel(int idHotel, String nombreHotel, String direccion, String categoria, Destino destino, Proveedor proveedor, boolean estado) {
        this.idHotel = idHotel;
        this.nombreHotel = nombreHotel;
        this.direccion = direccion;
        this.categoria = categoria;
        this.destino = destino;
        this.proveedor = proveedor;
        this.estado = estado;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombreHotel() {
        return nombreHotel;
    }

    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}