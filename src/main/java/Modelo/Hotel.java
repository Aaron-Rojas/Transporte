
package Modelo;

import java.util.Objects;

public class Hotel {

    private int idHotel;
    private String nombreHotel;
    private String direccion;
    private String categoria;
    private Destino destino;
    private Proveedor proveedor;    
    private boolean estado;

    public Hotel() {
    }

    public Hotel(int idHotel, String nombreHotel, String direccion, String categoria, Destino destino, Proveedor proveedor, boolean estado) {
        this.idHotel = idHotel;
        this.nombreHotel = nombreHotel;
        this.direccion = direccion;
        this.categoria = categoria;
        this.destino = destino;
        this.proveedor = proveedor;
        this.estado = true;
    }

    public Hotel(String nombreHotel, String direccion, String categoria, Destino destino, Proveedor proveedor, boolean estado) {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    @Override
    public String toString(){
        return nombreHotel;
    }
    
    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return idHotel == hotel.idHotel; // La igualdad se basa en el ID
    }
    @Override
    public int hashCode() {
        return Objects.hash(idHotel); // El hash se basa en el ID
    }
    
}