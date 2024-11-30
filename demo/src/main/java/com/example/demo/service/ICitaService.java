package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Cita;

public interface ICitaService {
    
    public Cita insertar(Cita cita);

    public Optional<Cita> buscarPorId(Integer id);

    public Optional<Cita> buscarPorTurno(String turno);

    public List<Cita> buscarTodasLasCitas();

    public Cita actualizar(Cita cita);

    public Boolean eliminar(Integer id);
}
