package com.enlatados.proyect.enlatados.controller;

import com.enlatados.proyect.enlatados.entity.Vehiculo;
import com.enlatados.proyect.enlatados.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService servicio;

    @GetMapping
    public String verTodos(Model model) {
        model.addAttribute("vehiculos", servicio.obtenerTodos());
        return "vehiculos";
    }

    @PostMapping("/crear")
    public String crear(Vehiculo vehiculo) {
        servicio.crear(vehiculo);
        return "redirect:/vehiculos";
    }

    @PostMapping("/modificar")
    public String modificar(Vehiculo vehiculo) {
        servicio.modificar(vehiculo.getPlaca(), vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/eliminar/{placa}")
    public String eliminar(@PathVariable String placa) {
        servicio.eliminar(placa);
        return "redirect:/vehiculos";
    }
}
