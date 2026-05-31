package com.enlatados.proyect.enlatados.service;


import com.enlatados.proyect.enlatados.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private AlmacenDatos almacen;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RepartidorService repartidorService;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private AlmacenService almacenService;

    public List<Pedido> obtenerTodos() {
        return almacen.getListaPedidos().obtenerTodos();
    }

    public Pedido buscarPorNumero(int numero) {
        return almacen.getListaPedidos()
                .buscar(p -> p.getNumeroPedido() == numero);
    }

    public String crearPedido(String origen, String destino,
                              String cuiCliente, String cuiRepartidor,
                              String placaVehiculo, int numeroCajas,
                              String fecha) {


        if (clienteService.buscarPorCui(cuiCliente) == null) {
            return "ERROR: Cliente no encontrado";
        }

        if (almacenService.totalCajas() < numeroCajas) {
            return "ERROR: No hay suficientes cajas. Disponibles: "
                    + almacenService.totalCajas();
        }

        Repartidor repartidor = repartidorService.desencolar(cuiRepartidor);
        if (repartidor == null) {
            return "ERROR: Repartidor no disponible";
        }

        Vehiculo vehiculo = vehiculoService.desencolar(placaVehiculo);
        if (vehiculo == null) {
            repartidorService.encolar(repartidor);
            return "ERROR: Vehiculo no disponible";
        }

        List<Caja> cajas = almacenService.sacarCajas(numeroCajas);

        int numeroPedido = almacen.getSiguienteNumeroPedido();
        Pedido nuevo = new Pedido(numeroPedido, origen, destino,
                cuiCliente, cuiRepartidor, placaVehiculo, fecha);

        for (Caja caja : cajas) {
            nuevo.agregarCaja(caja);
        }

        almacen.getListaPedidos().agregar(nuevo);

        return "OK:" + numeroPedido;
    }

    public String completarPedido(int numero) {
        Pedido pedido = buscarPorNumero(numero);

        if (pedido == null) return "ERROR: Pedido no encontrado";
        if (pedido.getEstado().equals("COMPLETADO")) {
            return "ERROR: El pedido ya esta completado";
        }

        pedido.completar();
        Repartidor repartidor = new Repartidor(
                pedido.getCuiRepartidor(), "", "", "", "");
        repartidorService.encolar(repartidor);

        Vehiculo vehiculo = new Vehiculo(
                pedido.getPlacaVehiculo(), "", "", "", 0, "");
        vehiculoService.encolar(vehiculo);

        return "OK";
    }
}
