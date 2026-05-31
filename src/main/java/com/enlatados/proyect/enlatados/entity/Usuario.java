package com.enlatados.proyect.enlatados.entity;

public class Usuario {

        private int id;
        private String nombre;
        private String apellidos;
        private String contrasenia;

        public Usuario(int id, String nombre, String apellidos, String contrasenia) {
            this.id = id;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.contrasenia = contrasenia;
        }

        public int getId() { return id; }
        public String getNombre() { return nombre; }
        public String getApellidos() { return apellidos; }
        public String getContrasenia() { return contrasenia; }

        public void setId(int id) { this.id = id; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setApellidos(String apellidos) { this.apellidos = apellidos; }
        public void setContrasenia(String contrasenia) { this.contrasenia = this.contrasenia; }
    }

