package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Inventario;

public interface IInventarioService {
    
    public Inventario insertar(Inventario inventario);

    public Optional<Inventario> buscarPorId(Integer id);

    public List<Inventario> buscarTodoInventario();

    public Inventario actualizar(Inventario inventario);

    public Boolean eliminar(Integer id);

}
