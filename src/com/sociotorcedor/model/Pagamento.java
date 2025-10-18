/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Pagamento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int id; 
    private Torcedor torcedor; 
    private LocalDate dataPagamento;
    private double valorPago;
    private String metodoPagamento;

    
    public Pagamento() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Torcedor getTorcedor() {
        return torcedor;
    }

    public void setTorcedor(Torcedor torcedor) {
        this.torcedor = torcedor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }



    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}