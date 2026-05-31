package com.enlatados.proyect.enlatados.controller;

import com.enlatados.proyect.enlatados.entity.Pedido;
import com.enlatados.proyect.enlatados.entity.PedidoForm;
import com.enlatados.proyect.enlatados.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService servicio;

    @GetMapping
    public String verTodos(Model model) {
        model.addAttribute("pedidos", servicio.obtenerTodos());
        return "pedidos";
    }

    @GetMapping("/{numero}")
    public String verDetalle(@PathVariable int numero, Model model) {
        Pedido p = servicio.buscarPorNumero(numero);
        model.addAttribute("pedido", p);
        return "pedido-detalle";
    }

    @PostMapping("/crear")
    public String crear(PedidoForm form, Model model) {
        // Convertir fecha
        String fecha = form.getFecha();
        if (fecha != null && fecha.contains("-")) {
            String[] partes = fecha.split("-");
            fecha = partes[2] + "/" + partes[1] + "/" + partes[0];
        }

        String resultado = servicio.crearPedido(
                form.getOrigen(),
                form.getDestino(),
                form.getCuiCliente(),
                form.getCuiRepartidor(),
                form.getPlacaVehiculo(),
                form.getNumeroCajas(),
                fecha
        );

        if (resultado.startsWith("ERROR")) {
            model.addAttribute("error", resultado);
            model.addAttribute("pedidos", servicio.obtenerTodos());
            return "pedidos";
        }

        return "redirect:/pedidos";
    }

    @GetMapping("/completar/{numero}")
    public String completar(@PathVariable int numero) {
        servicio.completarPedido(numero);
        return "redirect:/pedidos";
    }
}