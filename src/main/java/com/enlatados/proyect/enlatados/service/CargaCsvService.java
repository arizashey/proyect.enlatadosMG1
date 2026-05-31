package com.enlatados.proyect.enlatados.service;

import com.enlatados.proyect.enlatados.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;

    @Service
    public class CargaCsvService {

        @Autowired
        private AlmacenDatos almacen;

        public String cargarUsuarios(MultipartFile archivo) {
            int ok = 0;
            try {
                BufferedReader lector = new BufferedReader(
                        new InputStreamReader(archivo.getInputStream()));
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] p = linea.split(";");
                    if (p.length < 4) continue;
                    almacen.getListaUsuarios().agregar(
                            new Usuario(Integer.parseInt(p[0].trim()),
                                    p[1].trim(), p[2].trim(), p[3].trim()));
                    ok++;
                }
                lector.close();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
            return "Usuarios cargados: " + ok;
        }


        public String cargarClientes(MultipartFile archivo) {
            int ok = 0;
            try {
                BufferedReader lector = new BufferedReader(
                        new InputStreamReader(archivo.getInputStream()));
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] p = linea.split(";");
                    if (p.length < 4) continue;
                    almacen.getArbolClientes().insertar(
                            new Cliente(p[0].trim(), p[1].trim(),
                                    p[2].trim(), p[3].trim(), ""));
                    ok++;
                }
                lector.close();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
            return "Clientes cargados: " + ok;
        }

        public String cargarRepartidores(MultipartFile archivo) {
            int ok = 0;
            try {
                BufferedReader lector = new BufferedReader(
                        new InputStreamReader(archivo.getInputStream()));
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] p = linea.split(";");
                    if (p.length < 5) continue;
                    almacen.getColaRepartidores().encolar(
                            new Repartidor(p[0].trim(), p[1].trim(),
                                    p[2].trim(), p[3].trim(), p[4].trim()));
                    ok++;
                }
                lector.close();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
            return "Repartidores cargados: " + ok;
        }

        public String cargarVehiculos(MultipartFile archivo) {
            int ok = 0;
            try {
                BufferedReader lector = new BufferedReader(
                        new InputStreamReader(archivo.getInputStream()));
                String linea;
                while ((linea = lector.readLine()) != null) {
                    String[] p = linea.split(";");
                    if (p.length < 6) continue;
                    almacen.getColaVehiculos().encolar(
                            new Vehiculo(p[0].trim(), p[1].trim(), p[2].trim(),
                                    p[3].trim(), Integer.parseInt(p[4].trim()),
                                    p[5].trim()));
                    ok++;
                }
                lector.close();
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
            return "Vehículos cargados: " + ok;
        }
    }
