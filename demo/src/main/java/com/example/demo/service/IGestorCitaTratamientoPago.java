package com.example.demo.service;

import com.example.demo.repository.model.Cita;
import com.example.demo.repository.model.Pago;

public interface IGestorCitaTratamientoPago {
    
    public Cita insertarCitaPago(Cita cita, String tratamiento, Pago Pago, String cedulaDentista, String cedulaPaciente, String cedulaAdministradro);


}
