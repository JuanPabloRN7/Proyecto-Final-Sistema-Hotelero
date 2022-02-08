/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Jainer Pinta
 */
public class Rol {
    private String cargo;
    private boolean autorizacion;
    
    /**
     * Obtnener el booleano autorizacion de la clase Rol.
     * @return Retorna true si esta autorizado.
     */
    public boolean isAutorizacion() {
        return autorizacion;
    }
    
    /**
     * Setear el valor de autorizacion.
     * @param autorizacion Boolean valor de autorizacion.
     */
    public void setAutorizacion(boolean autorizacion) {
        this.autorizacion = autorizacion;
    }

    /**
     * Obtener cargo del empleado.
     * @return String cargo del empleado.
     */
    public String getCargo() {
        return cargo;
    }
    
    /**
     * Setear cargo del empleado
     * @param cargo String cargo del empleado.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    /**
     * Valor string de la clase rol.
     * @return Retorna el cargo del empleado.
     */
    @Override
    public String toString() {
        return this.cargo;
    }
    
    
}
