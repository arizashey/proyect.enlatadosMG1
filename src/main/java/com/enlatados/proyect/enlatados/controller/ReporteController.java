package com.enlatados.proyect.enlatados.controller;

import com.enlatados.proyect.enlatados.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService servicio;

    @GetMapping
    public String verReportes() {
        servicio.generarTodos();
        return "reportes";
    }

    @GetMapping("/imagen/{nombre}")
    public ResponseEntity<Resource> verImagen(@PathVariable String nombre) {
        File archivo = new File("src/main/resources/static/reportes/" + nombre);
        if (!archivo.exists()) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new FileSystemResource(archivo);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
    }
