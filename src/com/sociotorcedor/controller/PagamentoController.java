/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.controller;

import com.sociotorcedor.dao.PagamentoDAO;
import com.sociotorcedor.dao.TorcedorDAO;
import com.sociotorcedor.model.Pagamento;
import com.sociotorcedor.model.Torcedor;
import com.sociotorcedor.observer.ObservableList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PagamentoController {

    private final PagamentoDAO pagamentoDAO;
    private final TorcedorDAO torcedorDAO;
    private final ObservableList<Pagamento> pagamentosObservaveis;

    public PagamentoController() {
        this.pagamentoDAO = new PagamentoDAO();
        this.torcedorDAO = new TorcedorDAO();
        this.pagamentosObservaveis = new ObservableList<>();
        this.pagamentosObservaveis.addAll(pagamentoDAO.listarTodos());
    }

    public void registrarPagamento(Torcedor torcedor, String dataTexto, double valor, String metodo) throws IllegalArgumentException {
        // bloco de validação dos dados vindos da View.
        if (torcedor == null) {
            throw new IllegalArgumentException("É obrigatório selecionar um torcedor.");
        }
        if (dataTexto == null || dataTexto.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo 'Data' é obrigatório.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O 'Valor' deve ser um número positivo.");
        }
        
        LocalDate dataPagamento;
        try {
            // define o formato esperado para a data e converte o texto.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataPagamento = LocalDate.parse(dataTexto, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de data inválido. Use DD/MM/AAAA.");
        }

        // Se a validação passar, cria o objeto Pagamento.
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setTorcedor(torcedor);
        novoPagamento.setDataPagamento(dataPagamento);
        novoPagamento.setValorPago(valor);
        novoPagamento.setMetodoPagamento(metodo);

        pagamentoDAO.salvar(novoPagamento);
        pagamentosObservaveis.add(novoPagamento);
    }

    public void excluir(int id) {
        pagamentoDAO.excluir(id);
        
        pagamentosObservaveis.getList().removeIf(pagamento -> pagamento.getId() == id);
        pagamentosObservaveis.ping();
    }

    //  View obter a lista de torcedores para o ComboBox.
    public List<Torcedor> listarTodosTorcedores() {
        return torcedorDAO.listarTodos();
    }
    
    //  View se "inscrever" na lista de pagamentos.
    public ObservableList<Pagamento> getPagamentosObservaveis() {
        return this.pagamentosObservaveis;
    }
    
    // filtrar pagamentos por torcedor 
    public List<Pagamento> listarPagamentosPorTorcedor(Torcedor torcedor) {
        if (torcedor == null) {
            return pagamentosObservaveis.getList(); // Retorna todos se nenhum torcedor for selecionado
        }
        return pagamentosObservaveis.getList().stream()
                .filter(p -> p.getTorcedor().getId() == torcedor.getId())
                .collect(Collectors.toList());
    }
}
