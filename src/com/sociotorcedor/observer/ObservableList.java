/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.observer;

import java.util.ArrayList;
import java.util.Collection;

public class ObservableList<K> extends GenericObservable<ArrayList<K>> {
    private final ArrayList<K> valores = new ArrayList<>();
    
    public void addAll(Collection<K> items) {
        valores.addAll(items);
        notifyObservers();
    }
    
    public void add(K valor){
        valores.add(valor);
        notifyObservers();
    }
    
    public void remove(int index){
        valores.remove(index);
        notifyObservers();
    }
    
    public void set(int index, K valor){
        if(index > -1 && index < valores.size()) {
            valores.set(index, valor);
        }
        notifyObservers();
    }
    
    public ArrayList<K> getList() {
        return valores;
    }
    
    public void ping(){
        notifyObservers(); 
    }
    
    @Override
    protected void notifyObservers() {
        ArrayList<K> copia = new ArrayList<>(valores);
        observadores.forEach(obs -> obs.execute(copia));
    }
}
