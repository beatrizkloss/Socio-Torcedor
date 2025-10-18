/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sociotorcedor.observer;

import java.util.ArrayList;

public abstract class GenericObservable<K> {
    protected ArrayList<Observer<K>> observadores = new ArrayList<>();
    
    public void sign(Observer<K> observador){
        observadores.add(observador);
    }
    
    protected abstract void notifyObservers();
}
