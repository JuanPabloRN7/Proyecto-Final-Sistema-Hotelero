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

    private @FXML
    BorderPane bpZona;
    private @FXML
    Button btnMA;
    @FXML
    private Button btnMR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void cambiarEscena(ActionEvent e) {
        if ((Button) e.getSource() == btnMA) {
            cargarEscena("/vista/Frm_ModuloAdministrativo.fxml");
        }
    }

    @FXML
    private void cambiarms(ActionEvent e) {
        //if (e.getSource() == btms) {
        cargarEscena("/vista/Frm_ModuloServicios.fxml");

        //}
    }

    @FXML
    private void cambiarEscenaMR(ActionEvent event) {
        cargarEscena("/vista/Frm_ModuloReserva.fxml");
    }
    
     @FXML
    private void cambiarmr(ActionEvent e){
            cargarEscena("/vista/Frm_ModuloRecepcion.fxml");  

        
    }

    private void cargarEscena(String direccioneEscena) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(direccioneEscena));
        bpZona.getChildren().remove(bpZona.getCenter());
        try {
            bpZona.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
