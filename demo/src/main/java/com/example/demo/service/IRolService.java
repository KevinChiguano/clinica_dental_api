package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Rol;

public interface IRolService {
    
    public Rol insertar(Rol rol);

    public Optional<Rol> buscarPorId(Integer id);

    public Optional<Rol> buscarPorNombre(String nombre);

    public List<Rol> buscarTodosLosRoles();

    public Rol actualizar(Rol rol);

    public Boolean eliminar(Integer id);

}
