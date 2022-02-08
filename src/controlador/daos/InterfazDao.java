/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import lista.controlador.Lista;


/**
 *
 * @author Jainer Pinta
 */
public interface InterfazDao<T> {
    public boolean guardar(T dato);
    public boolean modificar(T dato, int ID);
    public Lista<T> listar();
    
}
