/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.dao;

import com.sociotorcedor.model.Torcedor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TorcedorDAO {

    private final String NOME_ARQUIVO = "torcedores.dat";
    private final List<Torcedor> torcedores;

    public TorcedorDAO() {
        this.torcedores = carregarTorcedoresDoArquivo();
    }

    public List<Torcedor> listarTodos() {
        Collections.sort(torcedores, Comparator.comparingInt(Torcedor::getId));
        return this.torcedores;
    }

    public void salvar(Torcedor torcedor) {
        torcedor.setId(gerarNovoId());
        this.torcedores.add(torcedor);
        salvarTorcedoresNoArquivo();
    }
    
    public void atualizar(Torcedor torcedorParaAtualizar) {
        torcedores.removeIf(torcedor -> torcedor.getId() == torcedorParaAtualizar.getId());
        torcedores.add(torcedorParaAtualizar);
        salvarTorcedoresNoArquivo();
    }

    public void excluir(int id) {
        torcedores.removeIf(torcedor -> torcedor.getId() == id);
        salvarTorcedoresNoArquivo();
    }
    
    private void salvarTorcedoresNoArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(torcedores);
        } catch (Exception e) {
            System.out.println("ERRO ao salvar torcedores no arquivo: " + e.getMessage());
        }
    }

    private List<Torcedor> carregarTorcedoresDoArquivo() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            return (List<Torcedor>) ois.readObject();
        } catch (Exception e) {
            System.out.println("ERRO ao carregar torcedores do arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    private int gerarNovoId() {
        int maiorId = 0;
        for (Torcedor torcedor : torcedores) {
            if (torcedor.getId() > maiorId) {
                maiorId = torcedor.getId();
            }
        }
        return maiorId + 1;
    }
}