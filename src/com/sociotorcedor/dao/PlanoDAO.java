/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.dao;

import com.sociotorcedor.model.Plano;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PlanoDAO {

    private final String NOME_ARQUIVO = "planos.dat";
    private final List<Plano> planos;

    public PlanoDAO() {
        this.planos = carregarPlanosDoArquivo();
    }

    public List<Plano> listarTodos() {
        // ordena a lista por ID antes de retornar, para consistência.
        Collections.sort(planos, Comparator.comparingInt(Plano::getId));
        return this.planos;
    }

    public void salvar(Plano plano) {
        plano.setId(gerarNovoId());
        this.planos.add(plano);
        salvarPlanosNoArquivo();
    }
    
    public void atualizar(Plano planoParaAtualizar) {
        planos.removeIf(plano -> plano.getId() == planoParaAtualizar.getId());
        planos.add(planoParaAtualizar);
        salvarPlanosNoArquivo();
    }

    public void excluir(int id) {
        planos.removeIf(plano -> plano.getId() == id);
        salvarPlanosNoArquivo();
    }
    
    private void salvarPlanosNoArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(planos);
        } catch (Exception e) {
            System.out.println("ERRO ao salvar planos no arquivo: " + e.getMessage());
        }
    }

    private List<Plano> carregarPlanosDoArquivo() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            return (List<Plano>) ois.readObject();
        } catch (Exception e) {
            System.out.println("ERRO ao carregar planos do arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    private int gerarNovoId() {
        int maiorId = 0;
        for (Plano plano : planos) {
            if (plano.getId() > maiorId) {
                maiorId = plano.getId();
            }
        }
        return maiorId + 1;
    }
}