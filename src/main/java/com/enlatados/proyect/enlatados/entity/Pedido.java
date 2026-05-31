package com.enlatados.proyect.enlatados.entity;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

        private int numeroPedido;
        private String departamentoOrigen;
        private String departamentoDestino;
        private String fechaHoraInicio;
        private String estado;
        private String cuiCliente;
        private String cuiRepartidor;
        private String placaVehiculo;
        private List<Caja> cajas;
        private int numeroCajas;

        public Pedido(int numeroPedido, String origen, String destino,
                      String cuiCliente, String cuiRepartidor, String placaVehiculo,
                      String fechaHoraInicio) {
            this.numeroPedido = numeroPedido;
            this.departamentoOrigen = origen;
            this.departamentoDestino = destino;
            this.cuiCliente = cuiCliente;
            this.cuiRepartidor = cuiRepartidor;
            this.placaVehiculo = placaVehiculo;
            this.fechaHoraInicio = fechaHoraInicio;
            this.estado = "PENDIENTE";
            this.cajas = new ArrayList<>();
            this.numeroCajas = 0;
        }

        public void agregarCaja(Caja caja) {
            this.cajas.add(caja);
            this.numeroCajas = this.cajas.size();
        }

        public void completar() {
            this.estado = "COMPLETADO";
        }

        public int getNumeroPedido()
        { return numeroPedido; }
        public String getDepartamentoOrigen()
        { return departamentoOrigen; }
        public String getDepartamentoDestino()
        { return departamentoDestino; }
        public String getFechaHoraInicio()
        { return fechaHoraInicio; }
        public String getEstado()
        { return estado; }
        public String getCuiCliente()
        { return cuiCliente; }
        public String getCuiRepartidor()
        { return cuiRepartidor; }
        public String getPlacaVehiculo()
        { return placaVehiculo; }
        public List<Caja> getCajas()
        { return cajas; }
        public int getNumeroCajas()
        { return numeroCajas; }

        public void setEstado(String estado) { this.estado = estado; }
    }


