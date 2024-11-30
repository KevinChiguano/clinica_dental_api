package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IRolRepository;
import com.example.demo.repository.model.Rol;

@Service
public class RolServiceImpl implements IRolService{

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public Rol insertar(Rol rol) {
        
        try {
            return this.rolRepository.save(rol);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar rol", e);
        }
        
    }

    @Override
    public Optional<Rol> buscarPorId(Integer id) {
        
        try {
            return this.rolRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por id rol", e);
        }
        
    }

    @Override
    public Optional<Rol> buscarPorNombre(String nombre) {
        
        try {
            return this.rolRepository.findByNombre(nombre);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por nombre rol", e);
        }

    }

    @Override
    public List<Rol> buscarTodosLosRoles() {
        
        try {
            return this.rolRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los roles", e);
        }
        
    }

    @Override
    public Rol actualizar(Rol rol) {
        
        Optional<Rol> existeRol = this.rolRepository.findById(rol.getId());

        if(!existeRol.isPresent()){
            throw new RuntimeException("Error al buscar para actualizar rol");
        }
        
        return this.rolRepository.save(rol);
        
    }

    @Override
    public Boolean eliminar(Integer id) {
        
        try {
            
            Optional<Rol> rolExiste = this.buscarPorId(id);

            if(rolExiste.isPresent()){

                this.rolRepository.delete(rolExiste.get());
                return true;

            }else{
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar rol", e);
        }

    }

    
    
}
