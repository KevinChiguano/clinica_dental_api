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

import com.example.demo.repository.model.Cita;
import com.example.demo.service.ICitaService;
import com.example.demo.service.IGestorCitaTratamientoPago;
import com.example.demo.service.dto.CitaDTO;

@RestController
@CrossOrigin
@RequestMapping("/cita")
public class CitaController {
    
    @Autowired
    private ICitaService citaService;

    @Autowired
    private IGestorCitaTratamientoPago citaTratamientoPago;

    @PostMapping()
    public ResponseEntity<Cita> insertar(@RequestBody CitaDTO citaDTO){
        return null;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Cita>> buscarPorId(@PathVariable Integer id){
        Optional<Cita> cita = this.citaService.buscarPorId(id);
        return new ResponseEntity<>(cita, null, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cita>> buscarCitas(){
        return new ResponseEntity<>(this.citaService.buscarTodasLasCitas(), null, HttpStatus.OK);
    }

}
