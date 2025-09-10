package com.perfumeria.aquadebelen.aquadebelen.model.lotes;

import java.time.*;

import com.perfumeria.aquadebelen.aquadebelen.model.productos.Producto;
public class Sublote {
    private Integer id;
    private LocalDate caducidad;
    private int cantidad;
    private double costoUnitario;
    private Lote lote;
    private Producto producto;
}
