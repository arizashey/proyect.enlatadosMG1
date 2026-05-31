package com.enlatados.proyect.enlatados.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListaEnlazada<T> {

    // Nodo: guarda el dato y apunta al siguiente
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public void agregar(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    public T buscar(Predicate<T> condicion) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (condicion.test(actual.dato)) return actual.dato;
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminar(Predicate<T> condicion) {
        if (cabeza == null) return false;
        if (condicion.test(cabeza.dato)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (condicion.test(actual.siguiente.dato)) {
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
        Nodo actual = cabeza;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }

    public int getTamanio() { return tamanio; }
    public boolean estaVacia() { return cabeza == null; }
}