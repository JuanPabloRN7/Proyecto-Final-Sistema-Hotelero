/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Jainer Pinta
 */
public class Frm_VentanaGeneralController implements Initializable {
    private @FXML BorderPane bpZona;
    private @FXML Button btnMA;
    private @FXML Button btnMR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    /**
     * Carga la escena del modulo administrativo.
     */
    @FXML
    private void cambiarEscena() {
        cargarEscena("/vista/Frm_ModuloAdministrativo.fxml");
    }
    
    /**
     * Carga la escena del modulo servicios.
     */
    @FXML
    private void cambiarms() {
        cargarEscena("/vista/Frm_ModuloServicios.fxml");
    }
    
    /**
     * Carga la escena del modulo reserva.
     */
    @FXML
    private void cambiarEscenaMR() {
        cargarEscena("/vista/Frm_ModuloReserva.fxml");
    }
    
    /**
     * Carga la escena del modulo recepcion.
     */
    @FXML
    private void cambiarmr(){
        cargarEscena("/vista/Frm_ModuloRecepcion.fxml");         
    }
    
    /**
     * Carga una escena en la zona centra del BorderPane.
     * @param direccioneEscena Direccion del archivo FXML.
     */
    private void cargarEscena(String direccioneEscena) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(direccioneEscena));
        bpZona.getChildren().remove(bpZona.getCenter());
        try {
            bpZona.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Oculta el modulo administrativo
     */
    public void ocultarMA(){
        btnMA.setDisable(true);
        btnMA.setVisible(false);
    }
}
