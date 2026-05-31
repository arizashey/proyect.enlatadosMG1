package com.enlatados.proyect.enlatados.controller;

import com.enlatados.proyect.enlatados.service.CargaCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

    @Controller
    @RequestMapping("/carga")
    public class CargaCsvController {

        @Autowired
        private CargaCsvService servicio;

        @GetMapping
        public String verPagina() {
            return "carga";
        }

        @PostMapping("/usuarios")
        public String cargarUsuarios(@RequestParam("archivo") MultipartFile archivo,
                                     Model model) {
            String resultado = servicio.cargarUsuarios(archivo);
            model.addAttribute("mensaje", resultado);
            return "carga";
        }

        @PostMapping("/clientes")
        public String cargarClientes(@RequestParam("archivo") MultipartFile archivo,
                                     Model model) {
            String resultado = servicio.cargarClientes(archivo);
            model.addAttribute("mensaje", resultado);
            return "carga";
        }

        @PostMapping("/repartidores")
        public String cargarRepartidores(@RequestParam("archivo") MultipartFile archivo,
                                         Model model) {
            String resultado = servicio.cargarRepartidores(archivo);
            model.addAttribute("mensaje", resultado);
            return "carga";
        }

        @PostMapping("/vehiculos")
        public String cargarVehiculos(@RequestParam("archivo") MultipartFile archivo,
                                      Model model) {
            String resultado = servicio.cargarVehiculos(archivo);
            model.addAttribute("mensaje", resultado);
            return "carga";
        }
    }
