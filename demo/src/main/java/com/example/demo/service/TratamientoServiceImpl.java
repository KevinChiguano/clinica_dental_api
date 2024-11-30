package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ITratamientoRepository;
import com.example.demo.repository.model.Tratamiento;

@Service
public class TratamientoServiceImpl implements ITratamientoService{

    @Autowired
    private ITratamientoRepository tratamientoRepository;

    @Override
    public Tratamiento insertar(Tratamiento tratamiento) {
        
        try {
            return this.tratamientoRepository.save(tratamiento);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar tratamiento", e);
        }
        
    }

    @Override
    public Optional<Tratamiento> buscarPorId(Integer id) {
        
        try {
            return this.tratamientoRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por id tratamiento", e);
        }
        
    }

    @Override
    public List<Tratamiento> buscarTodosLosTratamientos() {
        
        try {
            return this.tratamientoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los tratamientos", e);
        }
        
    }

    @Override
    public Tratamiento actualizar(Tratamiento tratamiento) {
        
        Optional<Tratamiento> tratamientoExiste = this.tratamientoRepository.findById(tratamiento.getId());

        if(!tratamientoExiste.isPresent()){
            throw new RuntimeException("Error al buscar tratamiento para actualizar");
        }
        
        return this.tratamientoRepository.save(tratamiento);
    }

    @Override
    public Boolean eliminar(Integer id) {
        
        try {

            Optional<Tratamiento> tratamientoExiste = this.buscarPorId(id);

            if(tratamientoExiste.isPresent()){

                this.tratamientoRepository.delete(tratamientoExiste.get());
                return true;

            }else{
                return false;
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar tratamiento" , e);
        }
        
    }
    
}
