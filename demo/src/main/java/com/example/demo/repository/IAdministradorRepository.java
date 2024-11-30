package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.model.Administrador;

public interface IAdministradorRepository extends JpaRepository<Administrador, Integer>{

    public Optional<Administrador> findByUsuarioId(Integer id);
    
}
