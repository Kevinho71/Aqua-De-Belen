package com.perfumeria.aquadebelen.aquadebelen.lotes.model;

import java.time.*;

import com.perfumeria.aquadebelen.aquadebelen.productos.model.Producto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sublote")
public class Sublote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "caducidad")
    private LocalDate caducidad;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "costo_unitario")
    private double costoUnitario;

    @ManyToOne
    @JoinColumn(name="lote_id")
    private Lote lote;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
