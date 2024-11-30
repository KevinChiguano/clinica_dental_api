package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.model.Inventario;

public interface IInventarioRepository extends JpaRepository<Inventario, Integer>{
    
}
