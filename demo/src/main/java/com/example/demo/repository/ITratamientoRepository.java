package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.model.Tratamiento;

public interface ITratamientoRepository extends JpaRepository<Tratamiento, Integer>{

    public Optional<Tratamiento> findByNombre(String nombre);
 
}
