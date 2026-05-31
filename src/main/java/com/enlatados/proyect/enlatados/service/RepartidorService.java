package com.enlatados.proyect.enlatados.service;

import com.enlatados.proyect.enlatados.entity.Repartidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepartidorService {

    @Autowired
    private AlmacenDatos almacen;

    public List<Repartidor> obtenerTodos() {
        return almacen.getColaRepartidores().obtenerTodos();
    }

    public Repartidor buscarPorCui(String cui) {
        return almacen.getColaRepartidores()
                .buscar(r -> r.getCui().equals(cui));
    }

    public String crear(Repartidor repartidor) {
        if (buscarPorCui(repartidor.getCui()) != null) {
            return "Ya existe un repartidor con ese CUI";
        }
        almacen.getColaRepartidores().encolar(repartidor);
        return "Repartidor agregado correctamente";
    }

    public String modificar(String cui, Repartidor actualizado) {
        if (buscarPorCui(cui) == null) {
            return "Repartidor no encontrado";
        }
        almacen.getColaRepartidores().eliminar(r -> r.getCui().equals(cui));
        actualizado.setCui(cui);
        almacen.getColaRepartidores().encolar(actualizado);
        return "Repartidor actualizado correctamente";
    }

    public boolean eliminar(String cui) {
        return almacen.getColaRepartidores()
                .eliminar(r -> r.getCui().equals(cui));
    }

    public Repartidor desencolar(String cui) {
        Repartidor r = buscarPorCui(cui);
        if (r != null) {
            almacen.getColaRepartidores()
                    .eliminar(rep -> rep.getCui().equals(cui));
        }
        return r;
    }

    public void encolar(Repartidor repartidor) {
        almacen.getColaRepartidores().encolar(repartidor);
    }
}
