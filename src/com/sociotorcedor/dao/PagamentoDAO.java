/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.dao;

import com.sociotorcedor.model.Pagamento;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    private final String NOME_ARQUIVO = "pagamentos.dat";
    
 
    private final List<Pagamento> pagamentos;

    public PagamentoDAO() {
        this.pagamentos = carregarPagamentosDoArquivo();
    }

    // retorna a lista completa de pagamentos.
    public List<Pagamento> listarTodos() {
        return this.pagamentos;
    }

    // adiciona um novo pagamento à lista, gera um ID e persiste a lista no arquivo.
    public void salvar(Pagamento pagamento) {
        pagamento.setId(gerarNovoId());
        this.pagamentos.add(pagamento);
        salvarPagamentosNoArquivo();
    }

    // exclui um pagamento da lista com base no ID e persiste a alteração.
    public void excluir(int id) {
        pagamentos.removeIf(pagamento -> pagamento.getId() == id);
        salvarPagamentosNoArquivo();
    }
    
    //  encapsula a lógica de gravação no arquivo.
    private void salvarPagamentosNoArquivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(pagamentos);
        } catch (Exception e) {
            System.out.println("ERRO ao salvar pagamentos no arquivo: " + e.getMessage());
        }
    }

    // lê o arquivo e carrega a lista para a memória.
    private List<Pagamento> carregarPagamentosDoArquivo() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            return (List<Pagamento>) ois.readObject();
        } catch (Exception e) {
            System.out.println("ERRO ao carregar pagamentos do arquivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    // Método utilitário para gerar um novo ID.
    private int gerarNovoId() {
        int maiorId = 0;
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getId() > maiorId) {
                maiorId = pagamento.getId();
            }
        }
        return maiorId + 1;
    }
}