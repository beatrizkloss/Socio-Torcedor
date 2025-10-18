/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.observer;

public class Observable<K> extends GenericObservable<K>{
    private K valor;
    
    public void setValor(K valor){
        this.valor = valor;
        notifyObservers();
    }

    @Override
    protected void notifyObservers() {
        observadores.forEach(obs -> obs.execute(valor));
    }
}