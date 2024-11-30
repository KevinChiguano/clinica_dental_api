package com.example.demo.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventario")
@Setter
@Getter
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inven_id_seq")
    @SequenceGenerator(name = "inven_id_seq", sequenceName = "inven_id_seq", allocationSize = 1)
    @Column(name = "inven_id")
    private Integer id;

    @Column(name = "inven_material")
    private String material;

    @Column(name = "inven_cantidad")
    private Integer cantidad;

    @Column(name = "inven_umbral_minimo")
    private Integer umbralMinimo;

    @Column(name = "inven_proveedor")
    private String proveedor;

    @ManyToOne
    @JoinColumn(name = "inven_id_admin")
    @JsonIgnore
    private Administrador administrador;

    
}
