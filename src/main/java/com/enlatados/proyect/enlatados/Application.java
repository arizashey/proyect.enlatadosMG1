package com.enlatados.proyect.enlatados;

import com.enlatados.proyect.enlatados.entity.Usuario;
import com.enlatados.proyect.enlatados.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        Usuario admin = new Usuario(12345, "Juan", "Arevalo", "alkjf4567");
        usuarioService.crear(admin);
        System.out.println("=== Usuario de prueba creado ===");
        System.out.println("ID: 12345 | Contraseña: 201030");
    }
}
