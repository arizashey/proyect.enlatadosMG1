package com.enlatados.proyect.enlatados.entity;

public class PedidoForm {

    private String origen;
    private String destino;
    private String cuiCliente;
    private String cuiRepartidor;
    private String placaVehiculo;
    private int numeroCajas;
    private String fecha;

    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public String getCuiCliente() { return cuiCliente; }
    public String getCuiRepartidor() { return cuiRepartidor; }
    public String getPlacaVehiculo() { return placaVehiculo; }
    public int getNumeroCajas() { return numeroCajas; }
    public String getFecha() { return fecha; }

    public void setOrigen(String origen) { this.origen = origen; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setCuiCliente(String cuiCliente) { this.cuiCliente = cuiCliente; }
    public void setCuiRepartidor(String cuiRepartidor) { this.cuiRepartidor = cuiRepartidor; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }
    public void setNumeroCajas(int numeroCajas) { this.numeroCajas = numeroCajas; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
