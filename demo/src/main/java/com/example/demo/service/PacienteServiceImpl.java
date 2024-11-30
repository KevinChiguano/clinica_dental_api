package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IPacienteRepository;
import com.example.demo.repository.model.Paciente;

@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;


    @Override
    public Paciente insertar(Paciente paciente) {
        
        
        try {
            return this.pacienteRepository.save(paciente);
        } catch (Exception e) {
            throw new RuntimeException("Eror al insertar paciente", e);
        }
        
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        
        try {
            return this.pacienteRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por id paciente", e);
        }
        
    }

    @Override
    public Optional<Paciente> buscarPorUsuarioId(Integer id) {
        try {
            return this.pacienteRepository.findByUsuarioId(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar paciente por usuario id", e);
        }
    }

    @Override
    public List<Paciente> buscarTodosLosPacientes() {
        
        try {
            return this.pacienteRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los pacientes", e);
        }
        
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        
        Optional<Paciente> pacienteExiste = this.pacienteRepository.findById(paciente.getId());

        if(!pacienteExiste.isPresent()){
            throw new RuntimeException("Error al buscar paciente para actualizar");
        }
        
        return this.pacienteRepository.save(paciente);
    }

    @Override
    public Boolean eliminar(Integer id) {
        
        try {

            Optional<Paciente> pacienteExiste = this.pacienteRepository.findById(id);

            if(pacienteExiste.isPresent()){
                this.pacienteRepository.delete(pacienteExiste.get());
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar paciente", e);
        }
        
    }

    
    
}
