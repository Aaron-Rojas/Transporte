package Modelo;

public class Bus {
    private int idBus;
    private String placa;
    private String estado;

    private int capacidad; // Make sure this is present from previous correction

    public Bus() {
    }

    public Bus(int idBus, String placa, int capacidad, String estado) {
        this.idBus = idBus;
        this.placa = placa;
        this.capacidad = capacidad;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}