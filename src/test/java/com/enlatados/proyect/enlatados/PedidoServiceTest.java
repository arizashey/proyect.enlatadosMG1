package com.enlatados.proyect.enlatados;

import com.enlatados.proyect.enlatados.entity.*;
import com.enlatados.proyect.enlatados.service.*;
import com.enlatados.proyect.enlatados.structures.ListaEnlazada;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceTest {

    @Mock
    private AlmacenDatos almacen;

    @Mock
    private ClienteService clienteService;

    @Mock
    private RepartidorService repartidorService;

    @Mock
    private VehiculoService vehiculoService;

    @Mock
    private AlmacenService almacenService;

    @InjectMocks
    private PedidoService servicio;

    private ListaEnlazada<Pedido> listaPedidos;

    @Before
    public void setUp() {
        listaPedidos = new ListaEnlazada<>();
        when(almacen.getListaPedidos()).thenReturn(listaPedidos);
        when(almacen.getSiguienteNumeroPedido()).thenReturn(100);
    }

    @Test
    public void testCrearPedidoClienteNoExiste() {
        when(clienteService.buscarPorCui("100")).thenReturn(null);

        String r = servicio.crearPedido("Guatemala", "Quetzaltenango",
                "100", "200", "789RCV", 5, "27/05/2026");

        assertEquals("ERROR: Cliente no encontrado", r);
    }

    @Test
    public void testCrearPedidoSinCajas() {
        when(clienteService.buscarPorCui("100"))
                .thenReturn(new Cliente("297707000103", "Ana", "Lopez", "12345678", "Zona 1"));
        when(almacenService.totalCajas()).thenReturn(0);

        String r = servicio.crearPedido("Guatemala", "Quetzaltenango",
                "100", "200", "789RCV", 5, "27/05/2026");

        assertTrue(r.startsWith("ERROR"));
    }

    @Test
    public void testCrearPedidoExitoso() {
        when(clienteService.buscarPorCui("100"))
                .thenReturn(new Cliente("297707000103", "Ana", "Lopez", "12345678", "Zona 1"));
        when(almacenService.totalCajas()).thenReturn(10);
        when(repartidorService.desencolar("2977069930103"))
                .thenReturn(new Repartidor("2977069930103", "Pedro", "Gil", "A", "12345"));
        when(vehiculoService.desencolar("789RCV"))
                .thenReturn(new Vehiculo("789RCV", "Toyota", "Dyna", "Blanco", 2015, "Automatico"));
        when(almacenService.sacarCajas(5)).thenReturn(new java.util.ArrayList<>());

        String r = servicio.crearPedido("Guatemala", "Quetzaltenango",
                "297707000103", "2977069930103", "789RCV", 5, "27/05/2026");

        assertTrue(r.startsWith("OK"));
    }

    @Test
    public void testCompletarPedidoNoExiste() {
        String r = servicio.completarPedido(999);
        assertEquals("ERROR: Pedido no encontrado", r);
    }
}