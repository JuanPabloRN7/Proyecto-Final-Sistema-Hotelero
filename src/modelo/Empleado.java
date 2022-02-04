/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Jainer Pinta
 */
public class Empleado extends Persona{
    private String identificacion;
    private Rol rol;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Rol getRol() {
        if(rol == null)
            rol = new Rol();
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
}
