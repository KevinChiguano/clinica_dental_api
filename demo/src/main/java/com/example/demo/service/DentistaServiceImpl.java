package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IDentistaRepository;
import com.example.demo.repository.model.Dentista;

@Service
public class DentistaServiceImpl implements IDentistaService{

    @Autowired
    private IDentistaRepository dentistaRepository;

    @Override
    public Dentista insertar(Dentista dentista) {
        
        try {
            return this.dentistaRepository.save(dentista);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar dentista", e);
        }
        
    }

    @Override
    public Optional<Dentista> buscarPorId(Integer id) {
        
        try {
            return this.dentistaRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por id dentista", e);
        }
        
    }

    @Override
    public Optional<Dentista> buscarPorUsuarioId(Integer id) {
        try {
            return this.dentistaRepository.findByUsuarioId(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar dentista por usuario id", e);
        }
    }

    @Override
    public List<Dentista> buscarTodosLosDentistas() {
        
        try {
            return this.dentistaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los dentistas", e);
        }
        
    }

    @Override
    public Dentista actualizar(Dentista dentista) {
        
        Optional<Dentista> dentistaExiste = this.dentistaRepository.findById(dentista.getId());
        
        if(!dentistaExiste.isPresent()){
            throw new RuntimeException("Error al buscar dentista para actualizar");
        }

        return this.dentistaRepository.save(dentista);

    }

    @Override
    public Boolean eliminar(Integer id) {
        
        try {
            
            Optional<Dentista> dentistaExiste = this.dentistaRepository.findById(id);

            if(dentistaExiste.isPresent()){
                this.dentistaRepository.delete(dentistaExiste.get());
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar dentista", e);
        }
        
    }

    
    
}
