package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.model.Dentista;

public interface IDentistaRepository extends JpaRepository<Dentista, Integer>{

    public Optional<Dentista> findByUsuarioId(Integer id);
    
}
