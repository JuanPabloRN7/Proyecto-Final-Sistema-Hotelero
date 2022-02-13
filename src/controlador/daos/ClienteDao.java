/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import lista.controlador.Lista;
import modelo.Cliente;
import modelo.Persona;

/**
 *
 * @author pablo
 */
public class ClienteDao extends AdaptadorDaoCliente<Cliente> {

    //private Persona persona;
    private Cliente cliente;

    public ClienteDao() {
        super(Cliente.class);
    }

    public Cliente getCliente() {
         if (cliente == null) {
            cliente =  new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
    public boolean guardar() {
//        persona.setIdPersona(Long.valueOf(listar().sizeLista() + 1));
        return guardar1(cliente);
    }
    
    public Lista<Cliente> buscarCliente(String dato, Integer tipo){
        Lista<Cliente> lista = new Lista();
        Lista<Cliente> aux = listar();
        for (int i = 0; i < aux.sizeLista(); i++) {
            Cliente p = aux.consultarDatoPosicion(i);
            Boolean band = (tipo == 1) ? p.getApellidos().toLowerCase().contains(dato.toLowerCase()) : 
                    p.getNombres().toLowerCase().contains(dato.toLowerCase());
            if (band) {
                lista.insertarNodo(p);
            }
        }
        return lista;
    }
    
}
