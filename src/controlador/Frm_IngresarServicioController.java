/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.daos.ServicioDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lista.controlador.Lista;
import modelo.Servicio;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class Frm_IngresarServicioController implements Initializable {

    private ServicioDao serviciodao = new ServicioDao();
    
    @FXML
    private TableView<Servicio> Tabla;
    @FXML
    private TextField txServicio;
    @FXML
    private TextField txPrecio;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colServicio;
    @FXML
    private TableColumn<?, ?> colPrecio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        limpiar();
    }    
    
    private void cargarTabla(){
        Lista<Servicio> lista = new Lista<>();
        lista = serviciodao.listar();
        ObservableList<Servicio> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < lista.sizeLista(); i++) {
            listaFX.add(lista.consultarDatoPosicion(i));
        }
        colID.setCellValueFactory(new PropertyValueFactory("IdServicio"));
        colServicio.setCellValueFactory(new PropertyValueFactory("nombreServicio"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        Tabla.setItems(listaFX);
        //Tabla.getItems().setAll(listaFX);
    }
    
    @FXML
    private void ingresarServicio(){
        if(validar()){
            serviciodao.getServicio().setNombreServicio(txServicio.getText());
            serviciodao.getServicio().setPrecio(Double.parseDouble(txPrecio.getText()));
            if(serviciodao.guardar()){
                crearAlerta(AlertType.INFORMATION, "OK", "Datos Guardados", "Los datos han sido guardados");
                limpiar();
            }else{
                crearAlerta(AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
            }
        }else{
            crearAlerta(AlertType.ERROR, "Error", "Vacio", "Campos Vacios");
        }
    }
    
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    private boolean validar(){
        return txServicio.getText().trim().length()>0 && txPrecio.getText().trim().length()>0; 
    }
    
    private void limpiar(){
        serviciodao.setServicio(null);
        cargarTabla();
        txServicio.setText("");
        txPrecio.setText("");
    }
}
