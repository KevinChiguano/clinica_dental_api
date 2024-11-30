package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.model.Paciente;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer>{
    
    public Optional<Paciente> findByUsuarioId(Integer usuarioId);

}
