/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.controller;

import com.sociotorcedor.dao.PlanoDAO;
import com.sociotorcedor.dao.TorcedorDAO;
import com.sociotorcedor.model.Plano;
import com.sociotorcedor.model.Torcedor;
import com.sociotorcedor.observer.ObservableList;
import java.util.List;

public class TorcedorController {

    private final TorcedorDAO torcedorDAO;
    private final PlanoDAO planoDAO;
    private final ObservableList<Torcedor> torcedoresObservaveis;

    public TorcedorController() {
        this.torcedorDAO = new TorcedorDAO();
        this.planoDAO = new PlanoDAO();
        this.torcedoresObservaveis = new ObservableList<>();
        this.torcedoresObservaveis.addAll(torcedorDAO.listarTodos());
    }

    public void salvar(String nome, String cpf, String email, Plano plano) throws IllegalArgumentException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'Nome' é obrigatório.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'CPF' é obrigatório.");
        }
        if (plano == null) {
            throw new IllegalArgumentException("É obrigatório selecionar um 'Plano'.");
        }
        
        Torcedor novoTorcedor = new Torcedor();
        novoTorcedor.setNome(nome);
        novoTorcedor.setCpf(cpf);
        novoTorcedor.setEmail(email);
        novoTorcedor.setPlano(plano);

        torcedorDAO.salvar(novoTorcedor);
        torcedoresObservaveis.add(novoTorcedor);
    }
    
    public void atualizar(int id, String nome, String cpf, String email, Plano plano) throws IllegalArgumentException {
        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty() || plano == null) {
            throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos para atualizar.");
        }
        
        Torcedor torcedorAtualizado = new Torcedor();
        torcedorAtualizado.setId(id);
        torcedorAtualizado.setNome(nome);
        torcedorAtualizado.setCpf(cpf);
        torcedorAtualizado.setEmail(email);
        torcedorAtualizado.setPlano(plano);
        
        torcedorDAO.atualizar(torcedorAtualizado);
        
        List<Torcedor> listaAtual = torcedoresObservaveis.getList();
        for (int i = 0; i < listaAtual.size(); i++) {
            if (listaAtual.get(i).getId() == id) {
                torcedoresObservaveis.set(i, torcedorAtualizado);
                break;
            }
        }
    }
    
    public void excluir(int id) {
        torcedorDAO.excluir(id);
        
        torcedoresObservaveis.getList().removeIf(torcedor -> torcedor.getId() == id);
        torcedoresObservaveis.ping();
    }

    public List<Plano> listarTodosPlanos() {
        return planoDAO.listarTodos();
    }
    
    public ObservableList<Torcedor> getTorcedoresObservaveis() {
        return this.torcedoresObservaveis;
    }
}