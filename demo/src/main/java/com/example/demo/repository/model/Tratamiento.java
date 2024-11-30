package com.example.demo.repository.model;

import java.util.List;

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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tratamiento")
@Setter
@Getter
public class Tratamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trata_id_seq")
    @SequenceGenerator(name = "trata_id_seq", sequenceName = "trata_id_seq", allocationSize = 1)
    @Column(name = "trata_id")
    private Integer id;

    @Column(name = "trata_nombre")
    private String nombre;

    @Column(name = "trata_descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "tratamiento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;
}
