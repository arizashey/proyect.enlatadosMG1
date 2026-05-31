package com.enlatados.proyect.enlatados.service;

import com.enlatados.proyect.enlatados.entity.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private AlmacenDatos almacen;

    public List<Vehiculo> obtenerTodos() {
        return almacen.getColaVehiculos().obtenerTodos();
    }

    public Vehiculo buscarPorPlaca(String placa) {
        return almacen.getColaVehiculos()
                .buscar(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    public String crear(Vehiculo vehiculo) {
        if (buscarPorPlaca(vehiculo.getPlaca()) != null) {
            return "Ya existe un vehiculo con esa placa";
        }
        almacen.getColaVehiculos().encolar(vehiculo);
        return "Vehiculo agregado correctamente";
    }

    public String modificar(String placa, Vehiculo actualizado) {
        if (buscarPorPlaca(placa) == null) {
            return "Vehiculo no encontrado";
        }
        almacen.getColaVehiculos()
                .eliminar(v -> v.getPlaca().equalsIgnoreCase(placa));
        actualizado.setPlaca(placa);
        almacen.getColaVehiculos().encolar(actualizado);
        return "Vehiculo actualizado correctamente";
    }

    public boolean eliminar(String placa) {
        return almacen.getColaVehiculos()
                .eliminar(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    public Vehiculo desencolar(String placa) {
        Vehiculo v = buscarPorPlaca(placa);
        if (v != null) {
            almacen.getColaVehiculos()
                    .eliminar(veh -> veh.getPlaca().equalsIgnoreCase(placa));
        }
        return v;
    }

    public void encolar(Vehiculo vehiculo) {
        almacen.getColaVehiculos().encolar(vehiculo);
    }
}
