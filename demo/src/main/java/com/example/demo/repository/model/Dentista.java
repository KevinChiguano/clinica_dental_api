package com.example.demo.repository.model;

import java.util.List;

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


@Entity
@Table(name = "dentista")
@Data
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dent_id_seq")
    @SequenceGenerator(name = "dent_id_seq", sequenceName = "dent_id_seq", allocationSize = 1)
    @Column(name = "dent_id")
    private Integer id;

    @Column(name = "dent_especialidad")
    private String especialidad;

    @ManyToOne
    @JoinColumn(name = "dent_id_usua")
    private Usuario usuario;

    @OneToMany(mappedBy = "dentista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;
    
}
