/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import lista.controlador.Lista;
import modelo.ServiciosCliente;

/**
 *
 * @author Usuario
 */
public class ClienteServiciosDao extends AdaptadorDaoClienteServicio<ServiciosCliente>{
    ServiciosCliente servicios;
    
    
    public ClienteServiciosDao() {
        super(ServiciosCliente.class);
    }

    public ServiciosCliente getServicios() {
        if(servicios == null)
            servicios = new ServiciosCliente();
        return servicios;
    }

    public void setServicios(ServiciosCliente servicio) {
        this.servicios = servicio;
    }
    
    public boolean guardar(){
        return guardar(servicios);
    }

    public Lista<ServiciosCliente> BusquedaServicios(String text, Integer tipo) {
        Lista<ServiciosCliente> lista = new Lista();
        Lista<ServiciosCliente> aux = listar();
        for (int i = 0; i < aux.sizeLista(); i++) {
            ServiciosCliente servicios = aux.consultarDatoPosicion(i);
            Boolean band = (tipo == 1) ? servicios.getCliente().toLowerCase().contains(text.toLowerCase()) : 
                    servicios.getNombreServicio().toLowerCase().contains(text.toLowerCase());
            if (band) {
                lista.insertarNodo(servicios);
            }
        }
        return lista;
    }
}
