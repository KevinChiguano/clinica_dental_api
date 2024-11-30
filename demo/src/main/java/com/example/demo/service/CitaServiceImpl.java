package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ICitaRepository;
import com.example.demo.repository.model.Cita;

@Service
public class CitaServiceImpl implements ICitaService {

    @Autowired
    private ICitaRepository citaRepository;

    @Override
    public Cita insertar(Cita cita) {
        
        try {
            return this.citaRepository.save(cita);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar cita", e);
        }
        
    }

    @Override
    public Optional<Cita> buscarPorId(Integer id) {

        try {
            return this.citaRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar cita por id", e);
        }
    }

    @Override
    public Optional<Cita> buscarPorTurno(String turno) {
        try {
            return this.citaRepository.findByTurno(turno);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar cita por turno", e);
        }
    }


    @Override
    public List<Cita> buscarTodasLasCitas() {
        
        try {
            return this.citaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar citas", e);
        }
    }

    @Override
    public Cita actualizar(Cita cita) {
        
        Optional<Cita> citaExiste = this.citaRepository.findById(cita.getId());

        if(!citaExiste.isPresent()){
            throw new RuntimeException("Error al buscar cita para actualizar");
        }

        return this.citaRepository.save(cita);
    }

    @Override
    public Boolean eliminar(Integer id) {
        
        try {

            Optional<Cita> citaExiste = this.citaRepository.findById(id);

            if(citaExiste.isPresent()){
                this.citaRepository.delete(citaExiste.get());
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar cita", e);
        }
    }

    
    
}
