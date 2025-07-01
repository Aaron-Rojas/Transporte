package Modelo;

public class Bus {
    private int idBus;
    private String placa;
    private String estado;

    public Bus() {
    }

    public Bus(int idBus, String placa, String estado) {
        this.idBus = idBus;
        this.placa = placa;
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
}