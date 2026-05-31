package com.enlatados.proyect.enlatados.controller;

import com.enlatados.proyect.enlatados.entity.Repartidor;
import com.enlatados.proyect.enlatados.service.RepartidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/repartidores")
public class RepartidorController {

    @Autowired
    private RepartidorService servicio;

    @GetMapping
    public String verTodos(Model model) {
        model.addAttribute("repartidores", servicio.obtenerTodos());
        return "repartidores";
    }

    @PostMapping("/crear")
    public String crear(Repartidor repartidor) {
        servicio.crear(repartidor);
        return "redirect:/repartidores";
    }

    @PostMapping("/modificar")
    public String modificar(Repartidor repartidor) {
        servicio.modificar(repartidor.getCui(), repartidor);
        return "redirect:/repartidores";
    }

    @GetMapping("/eliminar/{cui}")
    public String eliminar(@PathVariable String cui) {
        servicio.eliminar(cui);
        return "redirect:/repartidores";
    }
}