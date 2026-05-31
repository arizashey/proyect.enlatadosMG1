package com.enlatados.proyect.enlatados.controller;

import com.enlatados.proyect.enlatados.entity.Usuario;
import com.enlatados.proyect.enlatados.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService servicio;

    @GetMapping("/")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/usuarios/login")
    public String login(@RequestParam int id,
                        @RequestParam String contrasena,
                        Model model) {
        Usuario u = servicio.login(id, contrasena);
        if (u != null) {
            return "redirect:/index";
        } else {
            model.addAttribute("error", "ID o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@RequestParam int id,
                            @RequestParam String nombre,
                            @RequestParam String apellidos,
                            @RequestParam String contrasena,
                            Model model) {

        if (servicio.buscarPorId(id) != null) {
            model.addAttribute("error", "Ya existe un usuario con ese ID");
            return "registro";
        }

        Usuario nuevo = new Usuario(id, nombre, apellidos, contrasena);
        servicio.crear(nuevo);
        model.addAttribute("exito", "Usuario creado correctamente. Ya puedes iniciar sesión.");
        return "registro";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}