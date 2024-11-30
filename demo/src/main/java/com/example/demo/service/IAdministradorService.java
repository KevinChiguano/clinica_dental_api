package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.repository.model.Administrador;

public interface IAdministradorService {
    
    public Administrador insertar(Administrador administrador);

    public Optional<Administrador> buscarPorId(Integer id);

    public Optional<Administrador> buscarPorUsuarioId(Integer id);

    public List<Administrador> buscarTodosLosAdministradores();

    public Administrador actualizar(Administrador administrador);

    public Boolean eliminar(Integer id);

}
