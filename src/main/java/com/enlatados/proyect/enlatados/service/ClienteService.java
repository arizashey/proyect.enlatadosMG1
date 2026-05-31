package com.enlatados.proyect.enlatados.service;

import com.enlatados.proyect.enlatados.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private AlmacenDatos almacen;

    public List<Cliente> obtenerTodos() {
        return almacen.getArbolClientes().obtenerTodos();
    }

    public Cliente buscarPorCui(String cui) {
        return almacen.getArbolClientes().buscar(cui);
    }

    public String crear(Cliente cliente) {
        if (buscarPorCui(cliente.getCui()) != null) {
            return "Ya existe un cliente con ese CUI";
        }
        almacen.getArbolClientes().insertar(cliente);
        return "Cliente creado correctamente";
    }

    public String modificar(String cui, Cliente actualizado) {
        if (buscarPorCui(cui) == null) {
            return "Cliente no encontrado";
        }
        actualizado.setCui(cui);
        almacen.getArbolClientes().insertar(actualizado);
        return "Cliente actualizado correctamente";
    }

    public boolean eliminar(String cui) {
        if (buscarPorCui(cui) == null) return false;
        almacen.getArbolClientes().eliminar(cui);
        return true;
    }
}
