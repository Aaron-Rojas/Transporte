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
     private boolean estado;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public ViajeProgramado() {
        this.estado = true; // Por defecto, nuevo viaje está activo
    }

    // Constructor para nuevo viaje
   public ViajeProgramado(int idBus, int idOrigen, int idDestinoFinal,
                          String fechaHoraSalida, String fechaHoraLlegadaEstimada, boolean estado) {
        this.idBus = idBus;
        this.idOrigen = idOrigen;
        this.idDestinoFinal = idDestinoFinal;
        setFechaHoraSalida(fechaHoraSalida);
        setFechaHoraLlegadaEstimada(fechaHoraLlegadaEstimada);
        this.estado = estado;
    }

    // Getters y Setters
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

    public void setFechaHoraSalida(String fechaHora) throws DateTimeParseException {
        this.fechaHoraSalida = LocalDateTime.parse(fechaHora, DATE_TIME_FORMATTER);
    }

    public String getFechaHoraSalidaFormateada() {
        return fechaHoraSalida != null ? fechaHoraSalida.format(DATE_TIME_FORMATTER) : "";
    }

    public LocalDateTime getFechaHoraLlegadaEstimada() {
        return fechaHoraLlegadaEstimada;
    }

    public void setFechaHoraLlegadaEstimada(LocalDateTime fechaHoraLlegadaEstimada) {
        this.fechaHoraLlegadaEstimada = fechaHoraLlegadaEstimada;
    }

    public void setFechaHoraLlegadaEstimada(String fechaHora) throws DateTimeParseException {
        this.fechaHoraLlegadaEstimada = LocalDateTime.parse(fechaHora, DATE_TIME_FORMATTER);
    }

    public String getFechaHoraLlegadaEstimadaFormateada() {
        return fechaHoraLlegadaEstimada != null ? fechaHoraLlegadaEstimada.format(DATE_TIME_FORMATTER) : "";
    }

    public boolean isActivo() {
        return estado;
    }

    public void setActivo(boolean estadoActivo) {
        this.estado = estado;
    }
}