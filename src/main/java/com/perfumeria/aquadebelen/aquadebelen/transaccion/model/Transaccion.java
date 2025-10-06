package com.perfumeria.aquadebelen.aquadebelen.transaccion.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.perfumeria.aquadebelen.aquadebelen.clientes.model.Cliente;

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
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "total_bruto")
    private double totalBruto;

    @Column(name = "descuento_total")
    private double descuentoTotal;

    @Column(name="total_neto")
    private double totalNeto;

    @Column(name="con_factura")
    private boolean conFactura = false;

    @ManyToOne
    @JoinColumn(name = "metodo_de_pago_id")
    private MetodoDePago metodoDePago;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

   
    @OneToMany(mappedBy = "transaccion" ,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY)
    private List<DetalleTransaccion> detallesTransaccion;

    @OneToOne(mappedBy = "transaccion",
                cascade = CascadeType.PERSIST)
    private Factura factura;

    //El monto se calculara en base a los detalles de transaccion
    public Transaccion(LocalDateTime fecha, double descuentoTotal, MetodoDePago metodoDePago, Cliente cliente, boolean conFactura) {
        this.fecha = fecha;
        this.descuentoTotal = descuentoTotal;
        this.metodoDePago = metodoDePago;
        this.cliente = cliente;
        this.conFactura = conFactura;
    }

    public void addDetalle (DetalleTransaccion d){
        if (this.detallesTransaccion==null){
            List<DetalleTransaccion> detalles= new ArrayList<>();
            this.setDetallesTransaccion(detalles); {       
            };
        }
        detallesTransaccion.add(d);
        d.setTransaccion(this);

    }

     @Override
    public String toString() {
        return "Transaccion [id=" + id + ", fecha=" + fecha + ", totalBruto=" + totalBruto + ", descuentoTotal=" + descuentoTotal
                + ", totalNeto=" + totalNeto + ", conFactura=" + conFactura + ", metodoDePago=" + metodoDePago
                + ", cliente=" + cliente + ", detallesTransaccion=" + detallesTransaccion + ", factura=" + factura
                + "]";
    }

}
