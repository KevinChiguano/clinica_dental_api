package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.model.Cita;

public interface ICitaRepository extends JpaRepository<Cita, Integer>{

    public Optional<Cita> findByTurno(String turno);
    
}
