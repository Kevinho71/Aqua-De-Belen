package com.perfumeria.aquadebelen.aquadebelen.transaccion.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="numero")
    private Integer numero;

    @Column(name="fecha_de_emision")
    private LocalDateTime fechaEmision;

    @Column(name="razon_social")
    private String razonSocial;

    @Column(name="nit")
    private String nit;

    @OneToOne
    @JoinColumn(name = "transaccion_id", unique=true, nullable = false)
    private Transaccion transaccion;
}
