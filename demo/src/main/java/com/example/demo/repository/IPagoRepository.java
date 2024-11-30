package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.model.Pago;

public interface IPagoRepository extends JpaRepository<Pago, Integer>{
    
}
