/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import lista.controlador.Lista;
import modelo.Persona;

/**
 *
 * @author pablo
 */
public class ClienteDao extends AdaptadorDaoCliente<Persona> {

    private Persona persona;

    public ClienteDao() {
        super(Persona.class);
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public boolean guardar() {
//        persona.setIdPersona(Long.valueOf(listar().sizeLista() + 1));
        return guardar(persona);
    }
    
    public Lista<Persona> buscarCliente(String dato, Integer tipo){
        Lista<Persona> lista = new Lista();
        Lista<Persona> aux = listar();
        for (int i = 0; i < aux.sizeLista(); i++) {
            Persona p = aux.consultarDatoPosicion(i);
            Boolean band = (tipo == 1) ? p.getApellidos().toLowerCase().contains(dato.toLowerCase()) : 
                    p.getNombres().toLowerCase().contains(dato.toLowerCase());
            if (band) {
                lista.insertarNodo(p);
            }
        }
        return lista;
    }
    
}
