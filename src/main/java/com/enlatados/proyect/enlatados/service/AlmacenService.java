package com.enlatados.proyect.enlatados.service;

import com.enlatados.proyect.enlatados.entity.Caja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlmacenService {

    @Autowired
    private AlmacenDatos almacen;


    public List<Caja> obtenerTodas() {
        return almacen.getPilaCajas().obtenerTodos();
    }

    public int totalCajas() {
        return almacen.getPilaCajas().getTamanio();
    }


    public Caja agregarCaja(String fecha) {
        int correlativo = almacen.getSiguienteCorrelativoCaja();
        Caja nueva = new Caja(correlativo, fecha);
        almacen.getPilaCajas().push(nueva);
        return nueva;
    }


    public String inicializar(int cantidad, String fecha) {
        if (cantidad <= 0) return "La cantidad debe ser mayor a 0";
        for (int i = 0; i < cantidad; i++) {
            agregarCaja(fecha);
        }
        return "Se crearon " + cantidad + " cajas en el almacen";
    }


    public List<Caja> sacarCajas(int cantidad) {
        if (almacen.getPilaCajas().getTamanio() < cantidad) {
            return null;
        }
        List<Caja> cajasExtraidas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            cajasExtraidas.add(almacen.getPilaCajas().pop());
        }
        return cajasExtraidas;
    }
}

