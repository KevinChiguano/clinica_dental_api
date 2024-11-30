package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.model.Administrador;
import com.example.demo.repository.model.Cita;
import com.example.demo.repository.model.Dentista;
import com.example.demo.repository.model.Paciente;
import com.example.demo.repository.model.Pago;

import jakarta.transaction.Transactional;

@Service
public class GestorCitaTratamientoImpl implements IGestorCitaTratamientoPago {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICitaService citaService;

    @Autowired
    private IPagoService pagoService;

    @Override
    @Transactional
    public Cita insertarCitaPago(Cita cita, String tratamiento, Pago pago, String cedulaDentista,
                                  String cedulaPaciente, String cedulaAdministradro) {

        cita.setEstado(true);
        cita.setFechaCreacion(LocalDateTime.now());
        

        Paciente paciente = obtenerPaciente(cedulaPaciente);
        Dentista dentista = obtenerDentista(cedulaDentista);
        Administrador administrador = obtenerAdministrador(cedulaAdministradro);

        cita.setDentista(dentista);
        cita.setPaciente(paciente);
        cita.setAdministrador(administrador);

        this.citaService.insertar(cita);

        Cita citaPersistida = this.citaService.buscarPorTurno(cita.getTurno())
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada después de la inserción"));

        pago.setCita(citaPersistida);
        pago.setEstado(false);
        this.pagoService.insertar(pago);

        return citaPersistida;
    }

    private Paciente obtenerPaciente(String cedula) {
        return this.usuarioService.buscarPorCedula(cedula)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado para cédula: " + cedula))
                .getPacientes().getFirst();
    }

    private Dentista obtenerDentista(String cedula) {
        return this.usuarioService.buscarPorCedula(cedula)
                .orElseThrow(() -> new IllegalArgumentException("Dentista no encontrado para cédula: " + cedula))
                .getDentistas().getFirst();
    }

    private Administrador obtenerAdministrador(String cedula) {
        return this.usuarioService.buscarPorCedula(cedula)
                .orElseThrow(() -> new IllegalArgumentException("Administrador no encontrado para cédula: " + cedula))
                .getAdministradores().getFirst();
    }

}
