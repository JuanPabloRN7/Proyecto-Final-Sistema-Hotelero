/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.controlador;

import java.io.Serializable;
import lista.modelo.Nodo;

/**
 *
 * @author Jainer Pinta
 */
public class Cola <T> implements Serializable{
    private int size;
    private Nodo primero;
    private Nodo ultimo;

    public Cola(int size){
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Nodo getPrimero() {
        if (this.primero == null) {
            this.primero = new Nodo();
        }
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public Nodo getUltimo() {
        if (this.ultimo == null) {
            this.ultimo = new Nodo();
        }       
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
    
    public boolean estaLlena(){
        return size == tamanio();
    }
    
    public boolean estaVacia(){
        return this.primero == null;
    }
    
    public boolean queue(T dato){
        if (!estaLlena()) {
            Nodo temp = new Nodo();
            temp.setDato(dato);
            temp.setNodoSiguiente(null);
            if (estaVacia()) {
                primero = temp;
            }else{
                ultimo.setNodoSiguiente(temp);
            }
            ultimo = temp;
            return true;
        }
        return false;
    }
    
    public T dequeue(){
        if (!estaVacia()) {
            Nodo temp = new Nodo();
            temp = getPrimero();
            T dato = (T) temp.getDato();
            if (getSize()==1) {
                setUltimo(null);
            }
            setPrimero(temp.getNodoSiguiente());
            return dato;
        }
        return null;
    }
    
    public int tamanio(){
        Nodo tmp = primero;
        int contador = 0;
        while (!estaVacia() && tmp != null) {            
            contador++;
            tmp  = tmp.getNodoSiguiente();
        }
        return contador;
    }
    
    public T consultarDatoPosicion(int posicion){
        T dato = null;
        if (!estaVacia() && (posicion >= 0 && posicion <= tamanio()-1)) {       
            Nodo tmp = primero;        
            for (int i = 0; i < posicion; i++) {
                tmp = tmp.getNodoSiguiente();
                if (tmp == null)break;
            }
            if (tmp != null) {
                dato = (T)tmp.getDato();
            }
        }
        return dato;
    }
    
    public void imprimir(){
        Nodo tmp = primero;
        while (!estaVacia() && tmp != null) {            
            System.out.println(tmp.getDato());
            tmp  = tmp.getNodoSiguiente();
        }
    }
    
}
