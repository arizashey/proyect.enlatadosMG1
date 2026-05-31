package com.enlatados.proyect.enlatados;

import com.enlatados.proyect.enlatados.entity.Cliente;
import com.enlatados.proyect.enlatados.structures.ArbolAVL;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ArbolAVLTest {

    private ArbolAVL arbol;
    private Cliente cliente1;
    private Cliente cliente2;

    @Before
    public void setUp() {
        arbol = new ArbolAVL();
        cliente1 = new Cliente("100", "Ana", "Lopez", "12345678", "Zona 1");
        cliente2 = new Cliente("200", "Luis", "Garcia", "87654321", "Zona 2");
    }

    @Test
    public void testVacioAlInicio() {
        assertTrue(arbol.estaVacio());
    }

    @Test
    public void testInsertar() {
        arbol.insertar(cliente1);
        assertFalse(arbol.estaVacio());
    }

    @Test
    public void testBuscar() {
        arbol.insertar(cliente1);
        assertNotNull(arbol.buscar("100"));
    }

    @Test
    public void testBuscarNoExistente() {
        arbol.insertar(cliente1);
        assertNull(arbol.buscar("999"));
    }

    @Test
    public void testEliminar() {
        arbol.insertar(cliente1);
        arbol.eliminar("100");
        assertNull(arbol.buscar("100"));
    }

    @Test
    public void testObtenerTodos() {
        arbol.insertar(cliente1);
        arbol.insertar(cliente2);
        assertEquals(2, arbol.obtenerTodos().size());
    }
}
