package com.example.demo.service.dto;

import java.time.LocalDateTime;

import com.example.demo.repository.model.Pago;

import lombok.Data;


@Data
public class CitaDTO {

    private Integer id;

    private String turno;

    private LocalDateTime fechaCita;

    private LocalDateTime fechaCreacion;

    private Boolean estado;

    private String notas;

    private String cedulaPaciente;

    private String cedulaDentista;

    private String cedulaAdministrador;

    private Pago pago;

    private String tratamiento;
    
}
