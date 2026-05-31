package com.enlatados.proyect.enlatados;

import com.enlatados.proyect.enlatados.structures.ListaEnlazada;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ListaEnlazadaTest {

    private ListaEnlazada<String> lista;

    @Before
    public void setUp() {
        lista = new ListaEnlazada<>();
    }

    @Test
    public void testVaciaAlInicio() {
        assertTrue(lista.estaVacia());
    }

    @Test
    public void testAgregar() {
        lista.agregar("dato1");
        assertEquals(1, lista.getTamanio());
    }

    @Test
    public void testBuscar() {
        lista.agregar("dato1");
        assertNotNull(lista.buscar(e -> e.equals("dato1")));
    }

    @Test
    public void testEliminar() {
        lista.agregar("dato1");
        assertTrue(lista.eliminar(e -> e.equals("dato1")));
    }
}