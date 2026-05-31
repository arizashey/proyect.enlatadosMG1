package com.enlatados.proyect.enlatados;

import com.enlatados.proyect.enlatados.entity.Usuario;
import com.enlatados.proyect.enlatados.service.AlmacenDatos;
import com.enlatados.proyect.enlatados.service.UsuarioService;
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
public class UsuarioServiceTest {

    @Mock
    private AlmacenDatos almacen;

    @InjectMocks
    private UsuarioService servicio;

    @Before
    public void setUp() {
        when(almacen.getListaUsuarios()).thenReturn(new ListaEnlazada<>());
    }

    @Test
    public void testCrear() {
        String r = servicio.crear(new Usuario(1234, "Juan", "Perez", "1234"));
        assertEquals("Usuario creado correctamente", r);
    }

    @Test
    public void testLoginCorrecto() {
        servicio.crear(new Usuario(1234, "Juan", "Perez", "1234"));
        assertNotNull(servicio.login(1234, "1234"));
    }

    @Test
    public void testLoginIncorrecto() {
        servicio.crear(new Usuario(1234, "Juan", "Perez", "1234"));
        assertNull(servicio.login(1234, "wrong"));
    }

    @Test
    public void testEliminar() {
        servicio.crear(new Usuario(1234, "Juan", "Perez", "1234"));
        assertTrue(servicio.eliminar(1234));
    }
}

