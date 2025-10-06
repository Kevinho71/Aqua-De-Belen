package com.perfumeria.aquadebelen.aquadebelen.transaccion.viewmodel;

public class ListTransaccionViewModel {

    String transaccionId;
    String cliente;
    String totalNeto;
    String conFactura;
    String fecha;



    public ListTransaccionViewModel(){

    }



    public ListTransaccionViewModel(String transaccionId, String cliente, String totalNeto, String conFactura,
            String fecha) {
        this.transaccionId = transaccionId;
        this.cliente = cliente;
        this.totalNeto = totalNeto;
        this.conFactura = conFactura;
        this.fecha = fecha;
    }



    public String getTransaccionId() {
        return transaccionId;
    }



    public void setTransaccionId(String transaccionId) {
        this.transaccionId = transaccionId;
    }



    public String getCliente() {
        return cliente;
    }



    public void setCliente(String cliente) {
        this.cliente = cliente;
    }



    public String getTotalNeto() {
        return totalNeto;
    }



    public void setTotalNeto(String totalNeto) {
        this.totalNeto = totalNeto;
    }



    public String getConFactura() {
        return conFactura;
    }



    public void setConFactura(String conFactura) {
        this.conFactura = conFactura;
    }



    public String getFecha() {
        return fecha;
    }



    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
    
    
}
