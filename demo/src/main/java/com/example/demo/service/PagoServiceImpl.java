package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IPagoRepository;
import com.example.demo.repository.model.Pago;

@Service
public class PagoServiceImpl implements IPagoService{

    @Autowired
    private IPagoRepository pagoRepository;

    @Override
    public Pago insertar(Pago pago) {
        
        try {
            return this.pagoRepository.save(pago);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar pago", e);
        }
        
    }

    @Override
    public Optional<Pago> buscarPorId(Integer id) {
        
        try {
            return this.pagoRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar pago por id", e);
        }
        
    }

    @Override
    public List<Pago> buscarTodosLosPagos() {
        
        try {
            return this.pagoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todos los pagos", e);
        }
    }

    @Override
    public Pago actualizar(Pago pago) {
        
        Optional<Pago> pagoExiste = this.pagoRepository.findById(pago.getId());

        if(!pagoExiste.isPresent()){
            throw new RuntimeException("Error al buscar el pago para actualizar");
        }

        return this.pagoRepository.save(pago);

    }

    @Override
    public Boolean eliminar(Integer id) {
        
        try {
            
            Optional<Pago> pagoExiste = this.pagoRepository.findById(id);

            if(pagoExiste.isPresent()){

                this.pagoRepository.delete(pagoExiste.get());
                return true;

            }else{
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar pago", e);
        }
        
    }
    
}
