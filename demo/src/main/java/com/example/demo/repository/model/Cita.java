package com.example.demo.repository.model;

import java.time.LocalDateTime;
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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cita")
@Setter
@Getter
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cita_id_seq")
    @SequenceGenerator(name = "cita_id_seq", sequenceName = "cita_id_seq", allocationSize = 1)
    @Column(name = "cita_id")
    private Integer id;

    @Column(name = "cita_turno")
    private String turno;

    @Column(name = "cita_fecha_cita")
    private LocalDateTime fechaCita;

    @Column(name = "cita_fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "cita_estado")
    private Boolean estado;

    @Column(name = "cita_notas")
    private String notas;

    @ManyToOne
    @JoinColumn(name = "cita_id_trata")
    private Tratamiento tratamiento;
    
    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pago> pagos; 

    @ManyToOne
    @JoinColumn(name = "cita_id_admin")
    private Administrador administrador;

    @ManyToOne
    @JoinColumn(name = "cita_id_dent")
    private Dentista dentista;

    @ManyToOne
    @JoinColumn(name = "cita_id_paci")
    private Paciente paciente;
}
