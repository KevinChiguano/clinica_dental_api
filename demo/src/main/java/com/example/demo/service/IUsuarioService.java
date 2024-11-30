package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Usuario;

public interface IUsuarioService {
    
    public Usuario insertar(Usuario usuario);

    public Optional<Usuario> buscarPorId(Integer id);

    public Optional<Usuario> buscarPorCedula(String cedula);

    public List<Usuario> buscarTodosLosUsuarios();

    public Usuario actualizar(Usuario usuario);

    public Boolean eliminar(Integer id);




}
