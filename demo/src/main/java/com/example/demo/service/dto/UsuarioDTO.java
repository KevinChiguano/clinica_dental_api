package com.example.demo.service.dto;

import java.time.LocalDateTime;
import java.util.Map;


import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer id;

    private String nombre;

    private String cedula;

    private String password;

    private String email;

    private Boolean activo;

    private String telefono;

    private LocalDateTime fechaCreacion;

    private String rol;

    private Map<String, Object> atributos;

}
