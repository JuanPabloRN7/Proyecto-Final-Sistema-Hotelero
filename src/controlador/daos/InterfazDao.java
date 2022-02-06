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
    public boolean modificar(String dato, String ID);
    public boolean modificar(String nombre, String apellido, String telefono, String cargo, String id);
    public Lista<T> listar();
    
}
