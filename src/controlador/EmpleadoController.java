/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import lista.controlador.Lista;
import modelo.Empleado;
import modelo.Persona;

/**
 *
 * @author Jainer Pinta
 */
public class EmpleadoController extends PersonaController{
    private Empleado empleado;
    private Lista<Empleado> empleados;

    public Empleado getEmpleado() {
        if(empleado == null)
            empleado = new Empleado();
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Lista<Empleado> getEmpleados() {
        if(this.empleado == null)
            this.empleado = new Empleado();
        return this.empleados;
    }

    public void setEmpleados(Lista<Empleado> empleados) {
        this.empleados = empleados;
        empleados.setClazz(Empleado.class.getSuperclass());
    }

    
    public boolean asignarAutorizacion(String cargo){
        return (cargo.equalsIgnoreCase("recepcionista") || cargo.equalsIgnoreCase("gerente"));
    }
    
    public Lista<Empleado> buscarEmpleado(String dato, String atributo){
        Lista<Empleado> resultadoBusqueda = new Lista();
        resultadoBusqueda.setClazz(Empleado.class.getSuperclass());
        empleados.quicksort(0, empleados.sizeLista()-1, atributo, Lista.ASCENDENTE);
        if(atributo.equalsIgnoreCase("cedula")){
            Empleado resultado = empleados.busquedaBinaria(dato, atributo); 
            if(resultado != null){
                resultadoBusqueda.insertarNodo(resultado);
            }
        }else if(atributo.equalsIgnoreCase("identificacion")){
            resultadoBusqueda.setClazz(Empleado.class);
            empleados.setClazz(Empleado.class);
            Empleado resultado = empleados.busquedaBinaria(dato, atributo); 
            if(resultado != null){
                resultadoBusqueda.insertarNodo(resultado);
            }
        }else{
            resultadoBusqueda = empleados.busquedaSecuencial(dato, atributo);
        }
        return resultadoBusqueda;
    }
}
