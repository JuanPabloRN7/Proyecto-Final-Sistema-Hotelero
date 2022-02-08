/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import modelo.Reservacion;

/**
 *
 * @author pablo
 */
public class ReservaDao extends AdpatadorDaoReserva<Reservacion> {

    private Reservacion reserva;

    public ReservaDao() {
        super(Reservacion.class);
    }

    public Reservacion getReserva() {
        if(reserva == null){
            reserva = new Reservacion();
        }
        return reserva;
    }

    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    public boolean guardar() {
        return guardar(reserva);
    }

}
