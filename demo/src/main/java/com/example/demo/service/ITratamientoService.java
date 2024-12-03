package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Tratamiento;

public interface ITratamientoService {
    
    public Tratamiento insertar(Tratamiento tratamiento);

    public Optional<Tratamiento> buscarPorId(Integer id);

    public Optional<Tratamiento> buscarPorNombre(String nombre);

    public List<Tratamiento> buscarTodosLosTratamientos();

    public Tratamiento actualizar(Tratamiento tratamiento);

    public Boolean eliminar(Integer id);

}
