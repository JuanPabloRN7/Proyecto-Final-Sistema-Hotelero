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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */

public class Frm_ModuloServiciosController implements Initializable {

    private @FXML Button boton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     @FXML
    private void seleccionarVentana(ActionEvent e){
        if (e.getSource() == boton) {
            cargarVentana("/vista/Frm_IngresarServicio.fxml");
        }
        else{
            cargarVentana("/vista/Frm_IngresarClienteServicio.fxml");
        }
    }
    
    private void cargarVentana(String direccion){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(direccion));
            Scene escena1 = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(escena1);
            stage.setTitle("SERVICIOS");
            stage.show();
        }catch(IOException e){
            System.out.println("Problema"+e);
        } 
    }
    
}
