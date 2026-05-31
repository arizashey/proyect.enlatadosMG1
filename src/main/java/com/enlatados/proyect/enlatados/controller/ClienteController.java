package com.enlatados.proyect.enlatados.controller;

import com.enlatados.proyect.enlatados.entity.Cliente;
import com.enlatados.proyect.enlatados.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService servicio;

    @GetMapping
    public String verTodos(Model model) {
        model.addAttribute("clientes", servicio.obtenerTodos());
        return "clientes";
    }

    @PostMapping("/crear")
    public String crear(Cliente cliente) {
        servicio.crear(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/modificar")
    public String modificar(Cliente cliente) {
        servicio.modificar(cliente.getCui(), cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{cui}")
    public String eliminar(@PathVariable String cui) {
        servicio.eliminar(cui);
        return "redirect:/clientes";
    }
}