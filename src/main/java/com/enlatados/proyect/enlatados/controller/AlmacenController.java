package com.enlatados.proyect.enlatados.controller;

import com.enlatados.proyect.enlatados.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/almacen")
public class AlmacenController {

    @Autowired
    private AlmacenService servicio;

    @GetMapping
    public String verCajas(Model model) {
        model.addAttribute("cajas", servicio.obtenerTodas());
        model.addAttribute("total", servicio.totalCajas());
        return "almacen";
    }

    @PostMapping("/inicializar")
    public String inicializar(@RequestParam int cantidad,
                              @RequestParam String fecha) {
        servicio.inicializar(cantidad, fecha);
        return "redirect:/almacen";
    }

    @PostMapping("/agregar")
    public String agregar(@RequestParam String fecha) {
        servicio.agregarCaja(fecha);
        return "redirect:/almacen";
    }
}