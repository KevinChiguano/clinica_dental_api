package com.example.demo.service.dto;

import lombok.Data;

@Data
public class InventarioDTO {

    private Integer id;

    private String material;

    private Integer cantidad;

    private Integer umbralMinimo;

    private String proveedor;

    private String cedula;

}
