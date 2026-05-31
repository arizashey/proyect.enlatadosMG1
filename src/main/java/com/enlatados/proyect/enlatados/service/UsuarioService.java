package com.enlatados.proyect.enlatados.service;

import com.enlatados.proyect.enlatados.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private AlmacenDatos almacen;

    public List<Usuario> obtenerTodos() {
        return almacen.getListaUsuarios().obtenerTodos();
    }

    public Usuario buscarPorId(int id) {
        return almacen.getListaUsuarios()
                .buscar(u -> u.getId() == id);
    }

    public String crear(Usuario usuario) {
        if (buscarPorId(usuario.getId()) != null) {
            return "Ya existe un usuario con ese ID";
        }
        almacen.getListaUsuarios().agregar(usuario);
        return "Usuario creado correctamente";
    }

    public Usuario login(int id, String contrasenia) {
        return almacen.getListaUsuarios()
                .buscar(u -> u.getId() == id
                        && u.getContrasenia().equals(contrasenia));
    }

    public boolean eliminar(int id) {
        return almacen.getListaUsuarios()
                .eliminar(u -> u.getId() == id);
    }
}
