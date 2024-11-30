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
@Table(name = "paciente")
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_id_seq")
    @SequenceGenerator(name = "paciente_id_seq", sequenceName = "paciente_id_seq", allocationSize = 1)
    @Column(name = "paci_id")
    private Integer id;

    @Column(name = "paci_edad")
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "paci_id_usua")
    private Usuario usuario;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;


    
}
