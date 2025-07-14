package Modelo;

public class Bus {
    private int idBus;
    private String placa;
    private int capacidad;
    private boolean estado; // Cambiar a booleano para simplificar
    
    public Bus() {
    }
    
    public Bus(int idBus, String placa, int capacidad, boolean estado) {
        this.idBus = idBus;
        this.placa = placa;
        this.capacidad = capacidad;
        this.estado = estado;
    }
    
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

    // Getters y Setters
    public void setCapacidad(int capacidad) {    
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }
    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    // Método para obtener el estado como texto (opcional)
    public String getEstadoTexto() {
        return estado ? "operativo" : "inactivo";
    }
    
}