package Modelo;

public class Bus {
    private int idBus;
    private String placa;
    private int capacidad;
    private String estadoBus; // Cambiado a estadoBus para coincidir con la BD

    public Bus() {
    }

    public Bus(int idBus, String placa, int capacidad, String estadoBus) {
        this.idBus = idBus;
        this.placa = placa;
        this.capacidad = capacidad;
        this.estadoBus = estadoBus;
    }

    // Getters y Setters
    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstadoBus() {
        return estadoBus;
    }

    public void setEstadoBus(String estadoBus) {
        this.estadoBus = estadoBus;
    }
}