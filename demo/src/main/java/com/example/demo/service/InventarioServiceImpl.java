package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IInventarioRepository;
import com.example.demo.repository.model.Inventario;

@Service
public class InventarioServiceImpl implements IInventarioService{

    @Autowired
    private IInventarioRepository inventarioRepository;


    @Override
    public Inventario insertar(Inventario inventario) {
        
        try {
            return this.inventarioRepository.save(inventario);
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar en el inventario", e);
        }
        
    }

    @Override
    public Optional<Inventario> buscarPorId(Integer id) {
        
        try {
            return this.inventarioRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por id en inventario", e);
        }
        
    }

    @Override
    public List<Inventario> buscarTodoInventario() {
        
        try {
            return this.inventarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar todo en el inventario", e);
        }
        
    }

    @Override
    public Inventario actualizar(Inventario inventario) {
        
        Optional<Inventario> inventarioExiste = this.inventarioRepository.findById(inventario.getId());

        if(!inventarioExiste.isPresent()){
            throw new RuntimeException("Error al buscar el inventario para actualizar");
        }

        return this.inventarioRepository.save(inventario);
        
    }

    @Override
    public Boolean eliminar(Integer id) {
        
        try {

            Optional<Inventario> inventarioExiste = this.inventarioRepository.findById(id);

            if(inventarioExiste.isPresent()){

                this.inventarioRepository.delete(inventarioExiste.get());
                return true;

            }else{

                return false;

            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar en el inventario", e);
        }
        
    }
    
}
