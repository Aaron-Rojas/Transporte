package Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ViajeProgramado {
    private int idViajeProgramado;
    private int idBus;
    private int idOrigen;
    private int idDestinoFinal;
    private LocalDateTime fechaHoraSalida;
    private LocalDateTime fechaHoraLlegadaEstimada;
    private String estadoViaje;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ViajeProgramado() {}

    // Getters y Setters con validaciones
    public void setEstadoViaje(String estadoViaje) {
        if (!estadoViaje.matches("programado|en viaje|completado|cancelado")) {
            throw new IllegalArgumentException("Estado de viaje no válido");
        }
        this.estadoViaje = estadoViaje.toLowerCase();
    }
    
    public void setFechaHoraSalida(String fechaHora) throws DateTimeParseException {
        this.fechaHoraSalida = LocalDateTime.parse(fechaHora, DATE_TIME_FORMATTER);
    }

    public String getFechaHoraSalidaFormateada() {
        return fechaHoraSalida.format(DATE_TIME_FORMATTER);
    }

    public void setFechaHoraLlegadaEstimada(String fechaHora) throws DateTimeParseException {
        this.fechaHoraLlegadaEstimada = LocalDateTime.parse(fechaHora, DATE_TIME_FORMATTER);
    }

    public String getFechaHoraLlegadaEstimadaFormateada() {
        return fechaHoraLlegadaEstimada.format(DATE_TIME_FORMATTER);
    }

    // Resto de getters y setters
    public int getIdViajeProgramado() {
        return idViajeProgramado;
    }

    public void setIdViajeProgramado(int idViajeProgramado) {
        this.idViajeProgramado = idViajeProgramado;
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public int getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(int idOrigen) {
        this.idOrigen = idOrigen;
    }

    public int getIdDestinoFinal() {
        return idDestinoFinal;
    }

    public void setIdDestinoFinal(int idDestinoFinal) {
        this.idDestinoFinal = idDestinoFinal;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public LocalDateTime getFechaHoraLlegadaEstimada() {
        return fechaHoraLlegadaEstimada;
    }

    public void setFechaHoraLlegadaEstimada(LocalDateTime fechaHoraLlegadaEstimada) {
        this.fechaHoraLlegadaEstimada = fechaHoraLlegadaEstimada;
    }

    public String getEstadoViaje() {
        return estadoViaje;
    }
}