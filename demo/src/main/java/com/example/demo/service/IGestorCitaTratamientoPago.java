package com.example.demo.service;

import com.example.demo.repository.model.Cita;
import com.example.demo.service.dto.CitaDTO;

public interface IGestorCitaTratamientoPago {
    
    public Cita insertarCitaPago(CitaDTO citaDTO);


}
