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
    
    /**
     * Constructor predeterminado.
     */
    public EmpleadoDao() {
        super(Empleado.class);
    }  
    
    /**
     * Obtener empleado de la clase EmpleadoDao
     * @return Retorna un objeto de tipo Empleado.
     */
    public Empleado getEmpleado() {
        if(this.empleado == null)
            this.empleado = new Empleado();
        return empleado;
    }
    
    /**
     * Setea un objeto de tipo Empleado
     * @param empleado Objeto de tipo Empleado.
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    /**
     * Guarda el objeto empleado en la base de dato
     * @return Retorna true si el empleado se ha guardo.
     */
    public boolean guardar(){
        empleado.setIdPersona(Long.valueOf(listar().sizeLista()+1));
        return guardar(empleado);
    }
    
    public boolean modificar(){
        return modificar(empleado);
    }
    
}
