
package Modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
public class Itinerario {
    private int ID_Itinerario ;
    private Cliente cliente;
    private Usuario usuarioCreacion;
    private LocalDate FechaCreacion;
    private LocalDate FechaInicioViaje;
    private LocalDate FechaFinViaje;
    private BigDecimal Costo;
    private boolean Estado;

    public Itinerario(Cliente cliente, Usuario usuarioCreacion, LocalDate FechaCreacion, LocalDate FechaInicioViaje, LocalDate FechaFinViaje, BigDecimal Costo, boolean Estado) {
        this.cliente = cliente;
        this.usuarioCreacion = usuarioCreacion;
        this.FechaCreacion = FechaCreacion;
        this.FechaInicioViaje = FechaInicioViaje;
        this.FechaFinViaje = FechaFinViaje;
        this.Costo = Costo;
        this.Estado = Estado;
    }

    public Itinerario(int ID_Itinerario, Cliente cliente, Usuario usuarioCreacion, LocalDate FechaCreacion, LocalDate FechaInicioViaje, LocalDate FechaFinViaje, BigDecimal Costo, boolean Estado) {
        this.ID_Itinerario = ID_Itinerario;
        this.cliente = cliente;
        this.usuarioCreacion = usuarioCreacion;
        this.FechaCreacion = FechaCreacion;
        this.FechaInicioViaje = FechaInicioViaje;
        this.FechaFinViaje = FechaFinViaje;
        this.Costo = Costo;
        this.Estado = Estado;
    }



    public int getID_Itinerario() {
        return ID_Itinerario;
    }

    public void setID_Itinerario(int ID_Itinerario) {
        this.ID_Itinerario = ID_Itinerario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public LocalDate getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(LocalDate FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public LocalDate getFechaInicioViaje() {
        return FechaInicioViaje;
    }

    public void setFechaInicioViaje(LocalDate FechaInicioViaje) {
        this.FechaInicioViaje = FechaInicioViaje;
    }

    public LocalDate getFechaFinViaje() {
        return FechaFinViaje; 
    }

    public void setFechaFinViaje(LocalDate FechaFinViaje) {
        this.FechaFinViaje = FechaFinViaje;
    }

    public BigDecimal getCosto() {
        return Costo;
    }

    public void setCosto(BigDecimal Costo) {
        this.Costo = Costo;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
    // Sobrescritura del método toString() para una representación legible del objeto
    @Override
    public String toString() {
        return "Itinerario{" +
               "ID_Itinerario=" + ID_Itinerario +
               // Asegúrate de que Cliente y Usuario tengan un getter para su ID
               ", ID_Cliente=" + (cliente != null ? cliente.getIdCliente(): "null") +
               ", ID_UsuarioCreacion=" + (usuarioCreacion != null ? usuarioCreacion.getIdUsuario() : "null") +
               ", FechaCreacion=" + FechaCreacion +
               ", FechaInicioViaje=" + FechaInicioViaje +
               ", FechaFinViaje=" + FechaFinViaje +
               ", Costo=" + Costo +
               ", Estado=" + Estado + // Ya no es 'EstadoItinerario' String, sino 'Estado' boolean
               '}';
    }
}

