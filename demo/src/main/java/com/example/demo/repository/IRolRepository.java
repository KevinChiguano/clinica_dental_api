package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.model.Rol;

public interface IRolRepository extends JpaRepository<Rol, Integer>{

    Optional<Rol> findByNombre(String nombre);
    
}
