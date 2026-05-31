package com.enlatados.proyect.enlatados.structures;

import java.util.ArrayList;
import java.util.List;

public class Pila<T> {

    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    public void push(T dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }

    public T pop() {
        if (estaVacia()) return null;
        T dato = tope.dato;
        tope = tope.siguiente;
        tamanio--;
        return dato;
    }

    public T peek() {
        if (estaVacia()) return null;
        return tope.dato;
    }

    public List<T> obtenerTodos() {
        List<T> lista = new ArrayList<>();
        Nodo actual = tope;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }

    public boolean estaVacia() { return tope == null; }
    public int getTamanio() { return tamanio; }
}