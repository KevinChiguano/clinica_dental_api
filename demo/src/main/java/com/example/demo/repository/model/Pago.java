package com.example.demo.repository.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "pago")
@Setter
@Getter
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pago_id_seq")
    @SequenceGenerator(name = "pago_id_seq", sequenceName = "pago_id_seq", allocationSize = 1)
    @Column(name = "pago_id")
    private Integer id;

    @Column(name = "pago_valor")
    private BigDecimal valor;

    @Column(name = "pago_metodo_pago")
    private String metodoPago;

    @Column(name = "pago_estado")
    private Boolean estado;

    @Column(name = "pago_fecha")
    private LocalDateTime fecha;

    @Column(name = "pago_nota")
    private String nota;

    @ManyToOne
    @JoinColumn(name = "pago_id_cita")
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "pago_id_admin")
    private Administrador administrador;
    
}
