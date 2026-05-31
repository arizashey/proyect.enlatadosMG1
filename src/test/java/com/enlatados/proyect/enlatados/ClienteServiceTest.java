package com.enlatados.proyect.enlatados;

import com.enlatados.proyect.enlatados.entity.Cliente;
import com.enlatados.proyect.enlatados.service.AlmacenDatos;
import com.enlatados.proyect.enlatados.service.ClienteService;
import com.enlatados.proyect.enlatados.structures.ArbolAVL;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @Mock
    private AlmacenDatos almacen;

    @InjectMocks
    private ClienteService servicio;

    @Before
    public void setUp() {
        when(almacen.getArbolClientes()).thenReturn(new ArbolAVL());
    }

    @Test
    public void testCrear() {
        String r = servicio.crear(new Cliente("297707000103", "Ana", "Lopez", "12345678", "Zona 1"));
        assertEquals("Cliente creado correctamente", r);
    }

    @Test
    public void testCrearDuplicado() {
        servicio.crear(new Cliente("297707000103", "Ana", "Lopez", "12345678", "Zona 1"));
        String r = servicio.crear(new Cliente("297707000103", "Ana", "Lopez", "12345678", "Zona 1"));
        assertEquals("Ya existe un cliente con ese CUI", r);
    }

    @Test
    public void testBuscar() {
        servicio.crear(new Cliente("297707000103", "Ana", "Lopez", "12345678", "Zona 1"));
        assertNotNull(servicio.buscarPorCui("100"));
    }

    @Test
    public void testEliminar() {
        servicio.crear(new Cliente("2977070000103", "Ana", "Lopez", "12345678", "Zona 1"));
        assertTrue(servicio.eliminar("100"));
    }
}