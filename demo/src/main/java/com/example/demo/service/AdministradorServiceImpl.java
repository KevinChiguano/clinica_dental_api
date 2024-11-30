package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IAdministradorRepository;
import com.example.demo.repository.model.Administrador;

@Service
public class AdministradorServiceImpl implements IAdministradorService{

    @Autowired
    private IAdministradorRepository administradorRepository;

    @Override
    public Administrador insertar(Administrador administrador) {
        
        try {
            return this.administradorRepository.save(administrador);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar administrador", e);
        }

    }

    @Override
    public Optional<Administrador> buscarPorId(Integer id) {
        
        try {
            return this.administradorRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro al buscar administrador por id",e);
        }
    }

    @Override
    public Optional<Administrador> buscarPorUsuarioId(Integer id) {
        try {
            return this.administradorRepository.findByUsuarioId(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar administrador por usuario id", e);
        }
        
    }

    @Override
    public List<Administrador> buscarTodosLosAdministradores() {
        
        try {
            return this.administradorRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar administradores", e);
        }
    }

    @Override
    public Administrador actualizar(Administrador administrador) {
        
        Optional<Administrador> adminExiste = this.administradorRepository.findById(administrador.getId());

        if(!adminExiste.isPresent()){
            throw new RuntimeException("Administrador no encontrado para actualizar");
        }

        return this.administradorRepository.save(administrador);
        
    }

    @Override
    public Boolean eliminar(Integer id) {
        
        try {
            Optional<Administrador> adminExiste = this.administradorRepository.findById(id);

            if(adminExiste.isPresent()){
                this.administradorRepository.delete(adminExiste.get());
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar usuario", e);
        }

    }

    
    
}
