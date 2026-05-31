package com.enlatados.proyect.enlatados.entity;

public class Cliente {

        private String cui;
        private String nombre;
        private String apellidos;
        private String telefono;
        private String direccion;

        public Cliente(String cui, String nombre, String apellidos, String telefono, String direccion) {
            this.cui = cui;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.telefono = telefono;
            this.direccion = direccion;
        }

        public String getCui() { return cui; }
        public String getNombre() { return nombre; }
        public String getApellidos() { return apellidos; }
        public String getTelefono() { return telefono; }
        public String getDireccion() { return direccion; }

        public void
        setCui(String cui) { this.cui = cui; }
        public void
        setNombre(String nombre) { this.nombre = nombre; }
        public void
        setApellidos(String apellidos) { this.apellidos = apellidos; }
        public void
        setTelefono(String telefono) { this.telefono = telefono; }
        public void
        setDireccion(String direccion) { this.direccion = direccion; }
    }

