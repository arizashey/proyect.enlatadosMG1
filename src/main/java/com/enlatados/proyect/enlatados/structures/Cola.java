package com.enlatados.proyect.enlatados.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Cola<T> {

    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo frente;
    private Nodo final_;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.final_ = null;
        this.tamanio = 0;
    }

    public void encolar(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (estaVacia()) {
            frente = nuevo;
            final_ = nuevo;
        } else {
            final_.siguiente = nuevo;
            final_ = nuevo;
        }
        tamanio++;
    }

    public T desencolar() {
        if (estaVacia()) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) final_ = null;
        tamanio--;
        return dato;
    }

    public T buscar(Predicate<T> condicion) {
        Nodo actual = frente;
        while (actual != null) {
            if (condicion.test(actual.dato)) return actual.dato;
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminar(Predicate<T> condicion) {
        if (estaVacia()) return false;
        if (condicion.test(frente.dato)) {
            frente = frente.siguiente;
            if (frente == null) final_ = null;
            tamanio--;
            return true;
        }
        Nodo actual = frente;
        while (actual.siguiente != null) {
            if (condicion.test(actual.siguiente.dato)) {
                if (actual.siguiente == final_) final_ = actual;
                actual.siguiente = actual.siguiente.siguiente;
                tamanio--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public List<T> obtenerTodos() {
        List<T> lista = new ArrayList<>();
        Nodo actual = frente;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }

    public boolean estaVacia() { return frente == null; }
    public int getTamanio() { return tamanio; }
}
