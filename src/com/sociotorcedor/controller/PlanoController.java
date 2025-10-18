/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.controller;

import com.sociotorcedor.dao.PlanoDAO;
import com.sociotorcedor.model.Plano;
import com.sociotorcedor.observer.ObservableList; 
import java.util.List;


public class PlanoController {

    private final PlanoDAO planoDAO;
    private final ObservableList<Plano> planosObservaveis;

    public PlanoController() {
        this.planoDAO = new PlanoDAO();
        this.planosObservaveis = new ObservableList<>();
        
        // No momento da criação, o Controller pede os dados ao DAO e carrega
        this.planosObservaveis.addAll(planoDAO.listarTodos());
    }

    // mandar o DAO persistir o dado no arquivo.
    // atualizar a lista observável para notificar a View.
    public void salvar(String nome, double valor) throws IllegalArgumentException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'Nome do Plano' é obrigatório.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O campo 'Valor' deve ser um número positivo.");
        }
        
        Plano novoPlano = new Plano();
        novoPlano.setNome(nome);
        novoPlano.setValor(valor);

        planoDAO.salvar(novoPlano);
        
        // adiciona o novoPlano na lista observável, o que dispara a notificação.
        planosObservaveis.add(novoPlano);
    }
    
    public void atualizar(int id, String nome, double valor) throws IllegalArgumentException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'Nome do Plano' é obrigatório.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O campo 'Valor' deve ser um número positivo.");
        }
        
        Plano planoAtualizado = new Plano();
        planoAtualizado.setId(id);
        planoAtualizado.setNome(nome);
        planoAtualizado.setValor(valor);
        
        planoDAO.atualizar(planoAtualizado);
        
        List<Plano> listaAtual = planosObservaveis.getList();
        for (int i = 0; i < listaAtual.size(); i++) {
            if (listaAtual.get(i).getId() == id) {
                planosObservaveis.set(i, planoAtualizado);
                break;
            }
        }
    }
    
    public void excluir(int id) {
        planoDAO.excluir(id);
        
        // Remove da lista observável o item com o ID correspondente.
        planosObservaveis.getList().removeIf(plano -> plano.getId() == id);
        planosObservaveis.ping(); 
    }

    public List<Plano> listarTodos() {
        return planoDAO.listarTodos();
    }
    
    //  permite que a View tenha acesso à lista observável para poder se "inscrever".
    public ObservableList<Plano> getPlanosObservaveis() {
        return this.planosObservaveis;
    }
}