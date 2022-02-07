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

    public Cuenta getCuenta() {
        if(this.cuenta == null)
            this.cuenta = new Cuenta();
        return this.cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    public boolean guardar(){
        cuenta.setId(Long.valueOf(listar().sizeLista()+1));
        return guardar(cuenta);
    }
}
