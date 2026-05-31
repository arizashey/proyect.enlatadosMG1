package com.enlatados.proyect.enlatados.service;

import com.enlatados.proyect.enlatados.entity.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private AlmacenDatos almacen;

    private void generarImagen(String dot, String nombreArchivo) {
        try {
            String ruta = "src/main/resources/static/reportes/";
            File carpeta = new File(ruta);
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            MutableGraph g = new Parser().read(dot);
            Graphviz.fromGraph(g)
                    .width(800)
                    .render(Format.PNG)
                    .toFile(new File(ruta + nombreArchivo));
        } catch (Exception e) {
            System.out.println("Error generando reporte: " + e.getMessage());
        }
    }

    public void reporteUsuarios() {
        List<Usuario> usuarios = almacen.getListaUsuarios().obtenerTodos();
        StringBuilder dot = new StringBuilder();
        dot.append("digraph Usuarios {\n");
        dot.append("rankdir=LR;\n");
        dot.append("bgcolor=\"#ffffff\";\n");
        dot.append("node [shape=record, style=filled, fillcolor=\"#b3d4f5\", fontcolor=\"#2c3e50\", fontname=\"Arial\", fontsize=12];\n");
        dot.append("edge [color=\"#a0b4c8\", penwidth=1.5];\n");

        if (usuarios.isEmpty()) {
            dot.append("vacio [label=\"Lista vacía\", fillcolor=\"#e0e0e0\"];\n");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                dot.append("u" + i + " [label=\"{ID: " + u.getId()
                        + " | " + u.getNombre() + " " + u.getApellidos() + "}\"];\n");
            }
            for (int i = 0; i < usuarios.size() - 1; i++) {
                dot.append("u" + i + " -> u" + (i + 1) + ";\n");
            }
        }
        dot.append("}");
        generarImagen(dot.toString(), "usuarios.png");
    }

    public void reportePila() {
        List<Caja> cajas = almacen.getPilaCajas().obtenerTodos();
        StringBuilder dot = new StringBuilder();
        dot.append("digraph Pila {\n");
        dot.append("rankdir=TB;\n");
        dot.append("bgcolor=\"#ffffff\";\n");
        dot.append("node [shape=record, style=filled, fillcolor=\"#fde8a0\", fontcolor=\"#2c3e50\", fontname=\"Arial\", fontsize=12];\n");
        dot.append("edge [color=\"#c8b87a\", penwidth=1.5];\n");

        if (cajas.isEmpty()) {
            dot.append("vacio [label=\"Pila vacía\", fillcolor=\"#e0e0e0\"];\n");
        } else {
            dot.append("tope [label=\"TOPE\", shape=plaintext, fontcolor=\"#c0392b\", fontsize=13];\n");
            dot.append("tope -> c0;\n");
            for (int i = 0; i < cajas.size(); i++) {
                Caja c = cajas.get(i);
                dot.append("c" + i + " [label=\"{Corr: " + c.getCorrelativo()
                        + " | " + c.getFechaIngreso() + "}\"];\n");
            }
            for (int i = 0; i < cajas.size() - 1; i++) {
                dot.append("c" + i + " -> c" + (i + 1) + ";\n");
            }
        }
        dot.append("}");
        generarImagen(dot.toString(), "pila.png");
    }

    public void reporteArbolAVL() {
        List<Cliente> clientes = almacen.getArbolClientes().obtenerTodos();
        StringBuilder dot = new StringBuilder();
        dot.append("digraph ArbolAVL {\n");
        dot.append("bgcolor=\"#ffffff\";\n");
        dot.append("node [shape=circle, style=filled, fillcolor=\"#b5e8c8\", fontcolor=\"#2c3e50\", fontname=\"Arial\", fontsize=11];\n");
        dot.append("edge [color=\"#7fc49a\", penwidth=1.5];\n");

        if (clientes.isEmpty()) {
            dot.append("vacio [label=\"Árbol vacío\", shape=record, fillcolor=\"#e0e0e0\"];\n");
        } else {
            for (int i = 0; i < clientes.size(); i++) {
                Cliente c = clientes.get(i);
                dot.append("c" + i + " [label=\"" + c.getNombre() + "\\n" + c.getCui() + "\"];\n");
            }
            for (int i = 1; i < clientes.size(); i++) {
                dot.append("c" + ((i - 1) / 2) + " -> c" + i + ";\n");
            }
        }
        dot.append("}");
        generarImagen(dot.toString(), "avl.png");
    }

    public void reporteRepartidores() {
        List<Repartidor> repartidores = almacen.getColaRepartidores().obtenerTodos();
        StringBuilder dot = new StringBuilder();
        dot.append("digraph Cola {\n");
        dot.append("rankdir=LR;\n");
        dot.append("bgcolor=\"#ffffff\";\n");
        dot.append("node [shape=record, style=filled, fillcolor=\"#d5c5f0\", fontcolor=\"#2c3e50\", fontname=\"Arial\", fontsize=12];\n");
        dot.append("edge [color=\"#a090c8\", penwidth=1.5];\n");

        if (repartidores.isEmpty()) {
            dot.append("vacio [label=\"Cola vacía\", fillcolor=\"#e0e0e0\"];\n");
        } else {
            dot.append("frente [label=\"FRENTE\", shape=plaintext, fontcolor=\"#c0392b\", fontsize=13];\n");
            dot.append("final [label=\"FINAL\", shape=plaintext, fontcolor=\"#2980b9\", fontsize=13];\n");
            for (int i = 0; i < repartidores.size(); i++) {
                Repartidor r = repartidores.get(i);
                dot.append("r" + i + " [label=\"{" + r.getNombre()
                        + " | CUI: " + r.getCui() + "}\"];\n");
            }
            dot.append("frente -> r0;\n");
            for (int i = 0; i < repartidores.size() - 1; i++) {
                dot.append("r" + i + " -> r" + (i + 1) + ";\n");
            }
            dot.append("r" + (repartidores.size() - 1) + " -> final;\n");
        }
        dot.append("}");
        generarImagen(dot.toString(), "repartidores.png");
    }

    public void reporteVehiculos() {
        List<Vehiculo> vehiculos = almacen.getColaVehiculos().obtenerTodos();
        StringBuilder dot = new StringBuilder();
        dot.append("digraph Cola {\n");
        dot.append("rankdir=LR;\n");
        dot.append("bgcolor=\"#ffffff\";\n");
        dot.append("node [shape=record, style=filled, fillcolor=\"#f5b8c4\", fontcolor=\"#2c3e50\", fontname=\"Arial\", fontsize=12];\n");
        dot.append("edge [color=\"#c88090\", penwidth=1.5];\n");

        if (vehiculos.isEmpty()) {
            dot.append("vacio [label=\"Cola vacía\", fillcolor=\"#e0e0e0\"];\n");
        } else {
            dot.append("frente [label=\"FRENTE\", shape=plaintext, fontcolor=\"#c0392b\", fontsize=13];\n");
            dot.append("final [label=\"FINAL\", shape=plaintext, fontcolor=\"#2980b9\", fontsize=13];\n");
            for (int i = 0; i < vehiculos.size(); i++) {
                Vehiculo v = vehiculos.get(i);
                dot.append("v" + i + " [label=\"{" + v.getPlaca()
                        + " | " + v.getMarca() + " " + v.getModelo() + "}\"];\n");
            }
            dot.append("frente -> v0;\n");
            for (int i = 0; i < vehiculos.size() - 1; i++) {
                dot.append("v" + i + " -> v" + (i + 1) + ";\n");
            }
            dot.append("v" + (vehiculos.size() - 1) + " -> final;\n");
        }
        dot.append("}");
        generarImagen(dot.toString(), "vehiculos.png");
    }

    public void reportePedidos() {
        List<Pedido> pedidos = almacen.getListaPedidos().obtenerTodos();
        StringBuilder dot = new StringBuilder();
        dot.append("digraph Pedidos {\n");
        dot.append("rankdir=LR;\n");
        dot.append("bgcolor=\"#ffffff\";\n");
        dot.append("node [shape=record, style=filled, fontcolor=\"#2c3e50\", fontname=\"Arial\", fontsize=12];\n");
        dot.append("edge [color=\"#c8a080\", penwidth=1.5];\n");

        if (pedidos.isEmpty()) {
            dot.append("vacio [label=\"Lista vacía\", fillcolor=\"#e0e0e0\"];\n");
        } else {
            for (int i = 0; i < pedidos.size(); i++) {
                Pedido p = pedidos.get(i);
                String color = p.getEstado().equals("COMPLETADO") ? "#b5e8c8" : "#fde8a0";
                dot.append("p" + i + " [label=\"{#" + p.getNumeroPedido()
                        + " | " + p.getEstado() + "}\", fillcolor=\"" + color + "\"];\n");
            }
            for (int i = 0; i < pedidos.size() - 1; i++) {
                dot.append("p" + i + " -> p" + (i + 1) + ";\n");
            }
        }
        dot.append("}");
        generarImagen(dot.toString(), "pedidos.png");
    }

    public void generarTodos() {
        reporteUsuarios();
        reportePila();
        reporteArbolAVL();
        reporteRepartidores();
        reporteVehiculos();
        reportePedidos();
    }
}
