package com.enlatados.proyect.enlatados.entity;

public class Repartidor {

        private String cui;
        private String nombre;
        private String apellidos;
        private String licencia;
        private String telefono;

        public Repartidor(String cui, String nombre, String apellidos, String licencia, String telefono) {
            this.cui = cui;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.licencia = licencia;
            this.telefono = telefono;
        }

        public String getCui() { return cui; }
        public String getNombre() { return nombre; }
        public String getApellidos() { return apellidos; }
        public String getLicencia() { return licencia; }
        public String getTelefono() { return telefono; }

        public void setCui(String cui) { this.cui = cui; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setApellidos(String apellidos) { this.apellidos = apellidos; }
        public void setLicencia(String licencia) { this.licencia = licencia; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
    }

