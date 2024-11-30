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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "administrador")
@Setter
@Getter
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_id_seq")
    @SequenceGenerator(name = "admin_id_seq", sequenceName = "admin_id_seq", allocationSize = 1)
    @Column(name = "admin_id")
    private Integer id;


    @Column(name = "admin_codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "admin_id_usua")
    private Usuario usuario;

    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inventario> inventarios;

    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pago> pagos;

    @OneToMany(mappedBy = "administrador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cita> citas;
    
}
