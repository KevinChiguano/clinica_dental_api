package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{

    Optional<Usuario> findByCedula(String cedula);

    Optional<Usuario> findByEmail(String email);

}
