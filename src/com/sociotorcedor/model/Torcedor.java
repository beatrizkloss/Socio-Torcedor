/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.model;

import java.io.Serializable;

public class Torcedor implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private String cpf;
    private String email;
    
    private Plano plano;

    public Torcedor() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }
    
    // define qual texto vai aparecer na lista de seleção.
    @Override
    public String toString() {
        return this.nome; 
    }
}