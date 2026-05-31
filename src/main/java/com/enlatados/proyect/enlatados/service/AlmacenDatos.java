package com.enlatados.proyect.enlatados.service;

import com.enlatados.proyect.enlatados.entity.*;
import com.enlatados.proyect.enlatados.structures.*;
import org.springframework.stereotype.Service;

@Service
public class AlmacenDatos {

    private ListaEnlazada<Usuario> listaUsuarios;
    private Pila<Caja> pilaCajas;
    private ArbolAVL arbolClientes;
    private Cola<Repartidor> colaRepartidores;
    private Cola<Vehiculo> colaVehiculos;
    private ListaEnlazada<Pedido> listaPedidos;


    private int contadorCajas;
    private int contadorPedidos;

    public AlmacenDatos() {
        listaUsuarios    = new ListaEnlazada<>();
        pilaCajas        = new Pila<>();
        arbolClientes    = new ArbolAVL();
        colaRepartidores = new Cola<>();
        colaVehiculos    = new Cola<>();
        listaPedidos     = new ListaEnlazada<>();
        contadorCajas    = 1;
        contadorPedidos  = 100;
    }


    public ListaEnlazada<Usuario> getListaUsuarios() { return listaUsuarios; }
    public Pila<Caja> getPilaCajas() { return pilaCajas; }
    public ArbolAVL getArbolClientes() { return arbolClientes; }
    public Cola<Repartidor> getColaRepartidores() { return colaRepartidores; }
    public Cola<Vehiculo> getColaVehiculos() { return colaVehiculos; }
    public ListaEnlazada<Pedido> getListaPedidos() { return listaPedidos; }

    public int getSiguienteCorrelativoCaja() { return contadorCajas++; }
    public int getSiguienteNumeroPedido() { return contadorPedidos++; }
}
