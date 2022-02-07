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
    /**
     * Meotodo para guardar
     * @param dato Dato a guardar
     * @return True si se ha guardado
     */
    public boolean guardar(T dato);
    /**
     * Metodo para modificar
     * @param dato Dato a modificar.
     * @param ID Identificador del objeto a modificar.
     * @return Retorna true si se ha guardado.
     */
    public boolean modificar(String dato, String ID);
    /**
     * Metodo para modificar
     * @param nombre Nuevo nombre
     * @param apellido  Nuevo apellido
     * @param telefono Nuevo telefono
     * @param cargo Nuevo cargo
     * @param id Id del objeto a modificar.
     * @return True si se ha modificado.
     */
    public boolean modificar(String nombre, String apellido, String telefono, String cargo, String id);
    /**
     * Metodo para obtener una lista de objetos.
     * @return Una lista de de objetos.
     */
    public Lista<T> listar();
    
}
