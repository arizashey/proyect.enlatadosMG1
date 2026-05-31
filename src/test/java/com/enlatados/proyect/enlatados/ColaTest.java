package com.enlatados.proyect.enlatados;

import com.enlatados.proyect.enlatados.structures.Cola;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ColaTest {

    private Cola<String> cola;

    @Before
    public void setUp() {
        cola = new Cola<>();
    }

    @Test
    public void testVaciaAlInicio() {
        assertTrue(cola.estaVacia());
    }

    @Test
    public void testEncolar() {
        cola.encolar("repartidor1");
        assertEquals(1, cola.getTamanio());
    }

    @Test
    public void testDesencolar() {
        cola.encolar("repartidor1");
        String resultado = cola.desencolar();
        assertEquals("repartidor1", resultado);
    }

    @Test
    public void testDesencolarVacia() {
        assertNull(cola.desencolar());
    }

    @Test
    public void testOrdenFIFO() {
        cola.encolar("primero");
        cola.encolar("segundo");
        assertEquals("primero", cola.desencolar());
    }

    @Test
    public void testEliminar() {
        cola.encolar("repartidor1");
        assertTrue(cola.eliminar(e -> e.equals("repartidor1")));
    }
}