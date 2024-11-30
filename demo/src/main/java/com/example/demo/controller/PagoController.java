package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.model.Pago;
import com.example.demo.service.IPagoService;

@RestController
@CrossOrigin
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private IPagoService pagoService;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pago> actualizar(@RequestBody Pago pago){
        return new ResponseEntity<>(this.pagoService.actualizar(pago), null, HttpStatus.OK);
    }

    
}
