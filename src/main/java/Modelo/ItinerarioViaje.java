package Modelo;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ItinerarioViaje {
    private int idItinerarioViajeBus;
    private int idItinerario;
    private int idViajeProgramado;
    private String asientoAsignado;
    private double costo;
    private String estado;

    // Constructores
    public ItinerarioViaje() {}

    public ItinerarioViaje(int idItinerario, int idViajeProgramado, 
                             String asientoAsignado, double costo, String estado) {
        this.idItinerario = idItinerario;
        this.idViajeProgramado = idViajeProgramado;
        this.asientoAsignado = asientoAsignado;
        this.costo = costo;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdItinerarioViajeBus() {
        return idItinerarioViajeBus;
    }

    public void setIdItinerarioViajeBus(int idItinerarioViajeBus) {
        this.idItinerarioViajeBus = idItinerarioViajeBus;
    }

    public int getIdItinerario() {
        return idItinerario;
    }

    public void setIdItinerario(int idItinerario) {
        this.idItinerario = idItinerario;
    }

    public int getIdViajeProgramado() {
        return idViajeProgramado;
    }

    public void setIdViajeProgramado(int idViajeProgramado) {
        this.idViajeProgramado = idViajeProgramado;
    }

    public String getAsientoAsignado() {
        return asientoAsignado;
    }

    public void setAsientoAsignado(String asientoAsignado) {
        this.asientoAsignado = asientoAsignado;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    @Override
    public String toString() {
        return "ItinerarioViajeBus [ID: " + idItinerarioViajeBus + 
               ", Viaje: " + idViajeProgramado + 
               ", Asiento: " + asientoAsignado + "]";
    }
}