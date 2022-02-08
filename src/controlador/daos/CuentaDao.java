/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import modelo.Cuenta;

/**
 *
 * @author Jainer Pinta
 */
public class CuentaDao extends AdaptadorDaoCuenta<Cuenta>{
    private Cuenta cuenta;
    
    /**
     * Obtener objeto de tipo Cuenta.
     * @return Retorna un objeto de tipo Cuenta.
     */
    public Cuenta getCuenta() {
        if(this.cuenta == null)
            this.cuenta = new Cuenta();
        return this.cuenta;
    }
    
    /**
     * Setear objeto de tipo cuenta
     * @param cuenta Objeto de tipo Cuenta.
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    /**
     * Guarda la cuenta en la base de datos y setea un ID.
     * @return Retorna True si la cuenta se guardo.
     */
    public boolean guardar(){
        cuenta.setId(Long.valueOf(listar().sizeLista()+1));
        return guardar(cuenta);
    }
    
    public boolean modificar(){
        return modificar(cuenta);
    }
}
