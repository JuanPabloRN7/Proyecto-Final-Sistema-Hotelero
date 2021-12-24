/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import modelo.Servicio;

/**
 *
 * @author Usuario
 */
public class ServicioDao extends AdaptadorDaoServicio<Servicio>{
    Servicio servicio = new Servicio();
    
    
    public ServicioDao() {
        super(Servicio.class);
    }

    public Servicio getServicio() {
        if(servicio == null)
            servicio = new Servicio();
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
    public boolean guardar(){
        //servicio.setIdServicio(Long.valueOf(listar().sizeLista()+1));
        return guardar(servicio);
    }
}
