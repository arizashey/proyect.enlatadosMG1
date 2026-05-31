package com.enlatados.proyect.enlatados;

import com.enlatados.proyect.enlatados.structures.Pila;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PilaTest {

    private Pila<String> pila;

    @Before
    public void setUp() {
        pila = new Pila<>();
    }

    @Test
    public void testVaciaAlInicio() {
        assertTrue(pila.estaVacia());
    }

    @Test
    public void testPush() {
        pila.push("caja1");
        assertEquals(1, pila.getTamanio());
    }

    @Test
    public void testPop() {
        pila.push("caja1");
        String resultado = pila.pop();
        assertEquals("caja1", resultado);
    }

    @Test
    public void testPopVacia() {
        assertNull(pila.pop());
    }

    @Test
    public void testPeek() {
        pila.push("caja1");
        pila.push("caja2");
        assertEquals("caja2", pila.peek());
    }

    @Test
    public void testOrdenLIFO() {
        pila.push("primero");
        pila.push("segundo");
        assertEquals("segundo", pila.pop());
    }
}