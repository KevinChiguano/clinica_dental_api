package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.model.Paciente;

public interface IPacienteService {
    
    public Paciente insertar(Paciente paciente);

    public Optional<Paciente> buscarPorId(Integer id);

    public Optional<Paciente> buscarPorUsuarioId(Integer id);

    public List<Paciente> buscarTodosLosPacientes();

    public Paciente actualizar(Paciente paciente);

    public Boolean eliminar(Integer id);

}
