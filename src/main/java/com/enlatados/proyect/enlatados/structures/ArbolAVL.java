package com.enlatados.proyect.enlatados.structures;

import com.enlatados.proyect.enlatados.entity.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ArbolAVL {

    private class Nodo {
        Cliente cliente;
        Nodo izquierdo;
        Nodo derecho;
        int altura;

        Nodo(Cliente cliente) {
            this.cliente = cliente;
            this.izquierdo = null;
            this.derecho = null;
            this.altura = 1;
        }
    }

    private Nodo raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    private int altura(Nodo nodo) {
        if (nodo == null) return 0;
        return nodo.altura;
    }

    private void actualizarAltura(Nodo nodo) {
        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    // Factor de balance
    private int factorBalance(Nodo nodo) {
        if (nodo == null) return 0;
        return altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    private Nodo rotarDerecha(Nodo y) {
        Nodo x = y.izquierdo;
        Nodo T2 = x.derecho;
        x.derecho = y;
        y.izquierdo = T2;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }

    private Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.derecho;
        Nodo T2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = T2;
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }

    private Nodo balancear(Nodo nodo, String cui) {
        int balance = factorBalance(nodo);

        if (balance > 1 && cui.compareTo(nodo.izquierdo.cliente.getCui()) < 0)
            return rotarDerecha(nodo);

        if (balance < -1 && cui.compareTo(nodo.derecho.cliente.getCui()) > 0)
            return rotarIzquierda(nodo);

        if (balance > 1 && cui.compareTo(nodo.izquierdo.cliente.getCui()) > 0) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }

        if (balance < -1 && cui.compareTo(nodo.derecho.cliente.getCui()) < 0) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public void insertar(Cliente cliente) {
        raiz = insertarRecursivo(raiz, cliente);
    }

    private Nodo insertarRecursivo(Nodo nodo, Cliente cliente) {
        if (nodo == null) return new Nodo(cliente);

        int comparacion = cliente.getCui().compareTo(nodo.cliente.getCui());

        if (comparacion < 0)
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, cliente);
        else if (comparacion > 0)
            nodo.derecho = insertarRecursivo(nodo.derecho, cliente);
        else {
            nodo.cliente = cliente;
            return nodo;
        }

        actualizarAltura(nodo);
        return balancear(nodo, cliente.getCui());
    }

    public Cliente buscar(String cui) {
        return buscarRecursivo(raiz, cui);
    }

    private Cliente buscarRecursivo(Nodo nodo, String cui) {
        if (nodo == null) return null;
        int comparacion = cui.compareTo(nodo.cliente.getCui());
        if (comparacion == 0) return nodo.cliente;
        else if (comparacion < 0) return buscarRecursivo(nodo.izquierdo, cui);
        else return buscarRecursivo(nodo.derecho, cui);
    }

    public void eliminar(String cui) {
        raiz = eliminarRecursivo(raiz, cui);
    }

    private Nodo eliminarRecursivo(Nodo nodo, String cui) {
        if (nodo == null) return null;
        int comparacion = cui.compareTo(nodo.cliente.getCui());
        if (comparacion < 0)
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, cui);
        else if (comparacion > 0)
            nodo.derecho = eliminarRecursivo(nodo.derecho, cui);
        else {
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null) return nodo.izquierdo;
            Nodo sucesor = encontrarMinimo(nodo.derecho);
            nodo.cliente = sucesor.cliente;
            nodo.derecho = eliminarRecursivo(nodo.derecho, sucesor.cliente.getCui());
        }
        actualizarAltura(nodo);
        return balancear(nodo, cui);
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        inOrden(raiz, lista);
        return lista;
    }

    private void inOrden(Nodo nodo, List<Cliente> lista) {
        if (nodo == null) return;
        inOrden(nodo.izquierdo, lista);
        lista.add(nodo.cliente);
        inOrden(nodo.derecho, lista);
    }

    public boolean estaVacio() { return raiz == null; }
}