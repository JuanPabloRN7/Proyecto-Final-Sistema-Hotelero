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
public class Empleado extends Persona{
    private String identificacion;
    private Rol rol;
    
    /**
     * Obtener identificación unica de empleado
     * @return String identifiacion de empleado.
     */
    public String getIdentificacion() {
        return identificacion;
    }
    
    /**
     * Setear identificacion de empleado
     * @param identificacion String identificacion de empleado.
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
    /**
     * Obtener rol del empleado.
     * @return Objeto de tipo Rol.
     */
    public Rol getRol() {
        if(rol == null)
            rol = new Rol();
        return rol;
    }
    
    /**
     * Setear rol al empleado.
     * @param rol Objeto de tipo Rol.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
}
