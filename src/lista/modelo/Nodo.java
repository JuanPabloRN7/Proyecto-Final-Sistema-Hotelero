/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.modelo;

import java.io.Serializable;

/**
 *
 * @author Jainer Pinta
 */
public class Nodo <T> implements Serializable{
    private T dato;
    private Nodo nodoSiguiente;

    public Nodo(T dato, Nodo nodoSiguiente){
        this.dato = dato;
        this.nodoSiguiente = nodoSiguiente;
    }
    
    public Nodo(){
        this.dato = null;
        this.nodoSiguiente = null;
    }
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getNodoSiguiente() {
        return nodoSiguiente;
    }

    public void setNodoSiguiente(Nodo nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }
    
}
