/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lista.controlador.Lista;
import modelo.Cliente;

/**
 * FXML Controller class
 *
 * @author samy
 */
public class Frm_SalidaClienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Servicos
    private @FXML TextField txtservicio;
    private @FXML TextField txtPrecio;
    private @FXML TextField txtreservas;
    //hospedaje
   
    private @FXML TextField txtcliente;
    private @FXML TextField txtreservah;
    private @FXML TextField txthabitacion;
    private @FXML TextField txtestancia;
    private @FXML TextField txtprecioh;
    private @FXML TextField txttotal;
    
    private @FXML Button btnelegirs;
    private @FXML Button btnelegirsr;
    private @FXML Button btnguardar;
    private @FXML Button btnelegirrh;
    private @FXML VBox vBoxHospedaje;
    
    private @FXML TableView<Cliente> tblPagos;
    private @FXML TableColumn<?, ?> columServicio;
    private @FXML TableColumn<?, ?> columPrecioS;
    private @FXML TableColumn<?, ?> columCliente;
    private @FXML TableColumn<?, ?> columHabitacion;
    private @FXML TableColumn<?, ?> ColumEstancia;
    private @FXML TableColumn<?, ?> columPrecioH;
    private @FXML TableColumn<?, ?> columTotal;
    
    private @FXML TextField txtBuscar;
     //@FXML Button btnseleccionar;
    private @FXML ComboBox<?> cbxcriterio;
     @FXML Button btnseleccionar2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private void buscar() {
//        int select = cbxcriterio.getSelectionModel().getSelectedIndex();
//        System.out.println("index" +(int)cbxcriterio.getSelectionModel().getSelectedIndex() );
//        Lista<Cliente> aux = new Lista();
//        switch (select) {
//            case 0:
//                aux = clienteDao.listar1();
//                break;
//            case 1:
//                aux = clienteDao.buscarCliente(txtBuscar.getText(), 1);
//                break;
//            case 2:
//                aux = clienteDao.buscarCliente(txtBuscar.getText(), 2);
//                break;
//            default:
//                cargarTabla();
//        }
//        ObservableList<Cliente> listaFX = FXCollections.observableArrayList();
//        for (int i = 0; i < aux.sizeLista(); i++) {
//            listaFX.add((Cliente)aux.consultarDatoPosicion(i));
//        }
//        ingresarElementosTabla(listaFX);
//        txtBuscar.setText("");
   }
    
}
