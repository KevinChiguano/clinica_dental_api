package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.model.Tratamiento;
import com.example.demo.service.ITratamientoService;

@RestController
@CrossOrigin
@RequestMapping("/tratamiento")
public class TratamientoController {

    @Autowired
    private ITratamientoService tratamientoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tratamiento> insertar(@RequestBody Tratamiento tratamiento){
        return new ResponseEntity<>(this.tratamientoService.insertar(tratamiento), null, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Tratamiento>> buscarPorId(@PathVariable Integer id){
        Optional<Tratamiento> tratamiento = this.tratamientoService.buscarPorId(id);
        return new ResponseEntity<>(tratamiento, null, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tratamiento>> buscarTratamientos(){
        return new ResponseEntity<>(this.tratamientoService.buscarTodosLosTratamientos(), null, HttpStatus.OK);
    }

    
    
}
