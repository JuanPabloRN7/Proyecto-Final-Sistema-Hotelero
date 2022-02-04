/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Empleado;

/**
 *
 * @author Jainer Pinta
 */
public class EmpleadoController extends PersonaController{
    private Empleado empleado;

    public Empleado getEmpleado() {
        if(empleado == null)
            empleado = new Empleado();
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public boolean asignarAutorizacion(String cargo){
        return (cargo.equalsIgnoreCase("recepcionista") || cargo.equalsIgnoreCase("gerente"));
    }
}
