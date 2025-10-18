/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.model; 

import java.io.Serializable;


public class Plano implements Serializable {
    
    private static final long serialVersionUID = 1L;
 
    private int id;
    private String nome;
    private double valor;

    
    public Plano() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
     @Override
    public String toString() {
        return this.nome; 
    }
}
