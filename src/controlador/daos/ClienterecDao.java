
package controlador.daos;

import modelo.Cliente;
import javafx.scene.PerspectiveCamera;
import modelo.Persona;

/**
 *
 * @author jona-samy
 */
public class ClienterecDao extends AdaptadorDaoCliente<Cliente>{
    private Cliente cliente;
    

    public ClienterecDao() {
        super(Cliente.class);
    }

    public Cliente getCliente() {
       if(this.cliente == null)
            this.cliente  = new Cliente();
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
     public boolean guardar(){
        cliente.setIdPersona(Long.valueOf(listar().sizeLista()+1));
        return guardar(cliente);
    }
    
    
    
}
