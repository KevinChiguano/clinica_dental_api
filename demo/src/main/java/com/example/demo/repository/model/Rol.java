package com.example.demo.repository.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;



@Entity
@Table(name = "rol")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_id_seq")
    @SequenceGenerator(name = "rol_id_seq", sequenceName = "rol_id_seq", allocationSize = 1)
    @Column(name = "rol_id")
    private Integer id;

    @Column(name = "rol_nombre")
    private String nombre;
    
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Usuario> usuarios;

}
