package com.example.demo.service;


import com.example.demo.repository.model.Usuario;
import com.example.demo.service.dto.UsuarioDTO;

public interface IGestorUsuarioRolService {

    public Usuario insertarUsuarioConRol(UsuarioDTO usuarioDTO);

    public UsuarioDTO actualizarUsuarioRol(UsuarioDTO usuarioDTO);
    
}
