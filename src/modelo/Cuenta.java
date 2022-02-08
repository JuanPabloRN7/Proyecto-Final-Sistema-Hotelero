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
public class Cuenta {
    private Long id;
    private String identificacion;
    private String clave;
    
    /**
     * Obtener id de la cuenta.
     * @return Retorna un Long id.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Setear el id de la cuenta.
     * @param id Long id de la cuenta.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Obtener identificacion de empleado.
     * @return String identificacion de empleado.
     */
    public String getIdentificacion() {
        return identificacion;
    }
    
    /**
     * Setear identifiacion de empleado.
     * @param identificacion String identificacion de empleado.
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
    /**
     * Obtener clave de la cuenta
     * @return String clave de cuenta.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Setear clave de cuenta
     * @param clave String nueva clave.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
    
}
