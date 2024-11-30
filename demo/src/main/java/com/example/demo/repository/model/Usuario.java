package com.example.demo.repository.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
    @SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", allocationSize = 1)
    @Column(name = "usua_id")
    private Integer id;

    @Column(name = "usua_nombre")
    private String nombre;

    @Column(name = "usua_cedula")
    private String cedula;

    @Column(name = "usua_password")
    private String password;

    @Column(name = "usua_email")
    private String email;

    @Column(name = "usua_activo")
    private Boolean activo;

    @Column(name = "usua_telefono")
    private String telefono;

    @Column(name = "usua_fecha_creacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usua_id_rol")
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Paciente> pacientes;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JsonIgnore
    @ToString.Exclude
    private List<Dentista> dentistas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Administrador> administradores;

    
}
