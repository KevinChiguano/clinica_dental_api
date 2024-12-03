package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.model.Administrador;
import com.example.demo.repository.model.Cita;
import com.example.demo.repository.model.Dentista;
import com.example.demo.repository.model.Paciente;
import com.example.demo.repository.model.Pago;
import com.example.demo.repository.model.Tratamiento;
import com.example.demo.service.dto.CitaDTO;

import jakarta.transaction.Transactional;

@Service
public class GestorCitaTratamientoImpl implements IGestorCitaTratamientoPago {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICitaService citaService;

    @Autowired
    private IPagoService pagoService;

    @Autowired
    private ITratamientoService tratamientoService;

    @Override
    @Transactional
    public Cita insertarCitaPago(CitaDTO citaDTO) {

        citaDTO.setEstado(true);
        citaDTO.setFechaCreacion(LocalDateTime.now());
        

        Paciente paciente = obtenerPaciente(citaDTO.getCedulaPaciente());
        Dentista dentista = obtenerDentista(citaDTO.getCedulaDentista());
        Administrador administrador = obtenerAdministrador(citaDTO.getCedulaAdministrador());

        Tratamiento tratamiento = obtenerTratamiento(citaDTO.getTratamiento());

        Cita cita = this.convertirCita(citaDTO);

        cita.setDentista(dentista);
        cita.setPaciente(paciente);
        cita.setAdministrador(administrador);
        cita.setTratamiento(tratamiento);

        this.citaService.insertar(cita);

        Cita citaPersistida = this.citaService.buscarPorTurno(cita.getTurno())
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada después de la inserción"));

        Pago pago = new Pago();
        pago.setCita(citaPersistida);
        pago.setEstado(false);
        pago.setAdministrador(administrador);
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

    private Tratamiento obtenerTratamiento(String tratamiento) {
        return this.tratamientoService.buscarPorNombre(tratamiento)
                .orElseThrow(() -> new IllegalArgumentException("Tratamiento: " + tratamiento+" no encontrado."));
    }

    private Cita convertirCita(CitaDTO dto){
        Cita cita = new Cita();
        cita.setEstado(dto.getEstado());
        cita.setFechaCita(dto.getFechaCita());
        cita.setFechaCreacion(dto.getFechaCreacion());
        cita.setNotas(dto.getNotas());
        cita.setTurno(dto.getTurno());

        return cita;
    }

    private CitaDTO convertirCitaDTO(Cita cita){
        CitaDTO dto = new CitaDTO();
        dto.setId(cita.getId());
        dto.setEstado(cita.getEstado());
        dto.setFechaCita(cita.getFechaCita());
        dto.setFechaCreacion(cita.getFechaCreacion());
        dto.setNotas(cita.getNotas());
        dto.setTurno(cita.getTurno());

        return dto;
    }

}
