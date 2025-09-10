package com.perfumeria.aquadebelen.aquadebelen.model.lotes;
import java.time.*;
import java.util.List;

public class Lote {
    private Integer id;
    private LocalDateTime fechaIngreso;
    private String observacion;
    private List<Sublote> sublotes;
}
