package com.perfumeria.aquadebelen.aquadebelen.model.lotes.domain;
import java.time.*;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="lote")
public class Lote {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Column(name = "observacion")
    private String observacion;
    
    @OneToMany(mappedBy = "lote")
    private List<Sublote> sublotes;
}
