package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Pago;

public interface IPagoService {

    public Pago insertar(Pago pago);

    public Optional<Pago> buscarPorId(Integer id);

    public List<Pago> buscarTodosLosPagos();

    public Pago actualizar(Pago pago);

    public Boolean eliminar(Integer id);
    
}
