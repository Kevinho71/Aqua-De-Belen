package com.perfumeria.aquadebelen.aquadebelen.model.transaccion.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.perfumeria.aquadebelen.aquadebelen.model.clientes.domain.Cliente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "monto")
    private double monto;

    @Column(name = "descuento")
    private double descuento;
    
    @ManyToOne
    @JoinColumn(name = "metodo_de_pago_id")
    private MetodoDePago metodoDePago;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "transaccion" ,
                cascade = CascadeType.PERSIST)
    private List<DetalleTransaccion> detallesTransaccion = new ArrayList<>();

    @OneToOne(mappedBy = "transaccion",
                cascade = CascadeType.PERSIST)
    private Factura factura;

    //El monto se calculara en base a los detalles de transaccion
    public Transaccion(LocalDateTime fecha, double descuento, MetodoDePago metodoDePago, Cliente cliente) {
        this.fecha = fecha;
        this.descuento = descuento;
        this.metodoDePago = metodoDePago;
        this.cliente = cliente;
    }

    public void addDetalle (DetalleTransaccion d){
        detallesTransaccion.add(d);
        d.setTransaccion(this);

    }

    
}
