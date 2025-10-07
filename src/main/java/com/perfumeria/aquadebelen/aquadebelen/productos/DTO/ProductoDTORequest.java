package com.perfumeria.aquadebelen.aquadebelen.productos.DTO;

public record ProductoDTORequest (
    String nombre,
    Double precio,
    String descripcion,
    Integer tipoProductoId
    ){

}
