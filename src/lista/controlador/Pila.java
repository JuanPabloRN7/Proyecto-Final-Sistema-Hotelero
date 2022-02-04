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
public class Pila <T> implements Serializable{
    private Nodo cabecera;
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Pila(int size){
        this.size= size;
    }
    
    public Nodo getCabecera() {
        if(this.cabecera == null)
            this.cabecera = new Nodo();
        return cabecera;
    }

    public void setCabecera(Nodo cabecera) {
        this.cabecera = cabecera;
    }
    
    public boolean estaVacias(){
        return cabecera == null;
    }
    
    public boolean estaLlena(){
        return size == tamanio();
    }
    
    public boolean push(T dato){
        if (!estaLlena()) {
            Nodo temp = new Nodo();
            temp.setDato(dato);
            temp.setNodoSiguiente(cabecera);
            cabecera = temp;
            return true;
        }
        return false;
    }
    
    public boolean pop(){
        if (!estaVacias()) {
            //Nodo temp = new Nodo();
            Nodo temp = cabecera;
            cabecera = temp.getNodoSiguiente();
            return true;
        }
        return false;
    }
    
    public int tamanio(){
        Nodo tmp = cabecera;
        int contador = 0;
        while (!estaVacias() && tmp != null) {            
            contador++;
            tmp  = tmp.getNodoSiguiente();
        }
        return contador;
    }
    
    public T consultarDatoPosicion(int posicion){
        T dato = null;
        if (!estaVacias() && (posicion >= 0 && posicion <= tamanio()-1)) {       
            Nodo tmp = cabecera;        
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
        Nodo tmp = cabecera;
        while (!estaVacias() && tmp != null) {            
            System.out.println(tmp.getDato());
            tmp  = tmp.getNodoSiguiente();
        }
    }
}
