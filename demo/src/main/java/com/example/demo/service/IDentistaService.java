package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Dentista;


public interface IDentistaService {
    
    public Dentista insertar(Dentista dentista);

    public Optional<Dentista> buscarPorId(Integer id);

    public Optional<Dentista> buscarPorUsuarioId(Integer id);

    public List<Dentista> buscarTodosLosDentistas();

    public Dentista actualizar(Dentista dentista);

    public Boolean eliminar(Integer id);

}
