/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import modelo.ServiciosCliente;

/**
 *
 * @author Usuario
 */
public class ClienteServiciosDao extends AdaptadorDaoClienteServicio<ServiciosCliente>{
    ServiciosCliente servicios = new ServiciosCliente();
    
    
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
}
