/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import modelo.Empleado;

/**
 *
 * @author Jainer Pinta
 */
public class EmpleadoDao extends AdaptadorDaoEmpleado<Empleado>{
    private Empleado empleado;
    public EmpleadoDao() {
        super(Empleado.class);
    }  
    
    public Empleado getEmpleado() {
        if(this.empleado == null)
            this.empleado  = new Empleado();
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public boolean guardar(){
        empleado.setIdPersona(Long.valueOf(listar().sizeLista()+1));
        return guardar(empleado);
    }
    
}
