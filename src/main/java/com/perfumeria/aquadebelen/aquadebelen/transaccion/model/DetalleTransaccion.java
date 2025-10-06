    package com.perfumeria.aquadebelen.aquadebelen.transaccion.model;

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
    @Table(name = "detalle_transaccion")
    public class DetalleTransaccion {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "cantidad")
        private double cantidad;

        @Column(name = "subtotal")
        private double subtotal;

        @Column(name = "descuento")
        private double descuento;

        @ManyToOne
        @JoinColumn(name = "producto_id")
        private Producto producto;

        @ManyToOne
        @JoinColumn(name = "transaccion_id")
        private Transaccion transaccion;

        @Override
        public String toString() {
            return "DetalleTransaccion [id=" + id + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", producto="
                    + producto + ", transaccion=" + transaccion.getId() + "]";
        }

        
    }
