package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.repository.model.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Usuario insertar(Usuario usuario) {

        try {
            return this.usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar usuario", e);
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario;
    }

    @Override
    public Optional<Usuario> buscarPorCedula(String cedula) {
        Optional<Usuario> usuario = this.usuarioRepository.findByCedula(cedula);
        return usuario;
    }

    @Override
    public List<Usuario> buscarTodosLosUsuarios() {

        try {
            return this.usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar los usuarios", e);
        }

    }

    @Override
    public Usuario actualizar(Usuario usuario) {

        Optional<Usuario> usuarioExiste = this.usuarioRepository.findById(usuario.getId());

        if (!usuarioExiste.isPresent()) {
            throw new RuntimeException("Usuario no encontrado para actualizar");
        }

        return this.usuarioRepository.save(usuario);

    }

    @Override
    public Boolean eliminar(Integer id) {

        try {

            Optional<Usuario> usuarioExiste = this.usuarioRepository.findById(id);

            if (usuarioExiste.isPresent()) {
                this.usuarioRepository.delete(usuarioExiste.get());
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el usuario", e);
        }

    }

    

}
