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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jainer Pinta
 */
public class Frm_ModuloAdministrativoController implements Initializable {
    private @FXML Button btnAdd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void seleccionarVentana(ActionEvent e){
        if (e.getSource() == btnAdd) {
            cargarVentana("/vista/Frm_PanelAddEmpleado.fxml");
        }else{
            cargarVentana("/vista/Frm_AsignarCargo.fxml");
        }
    }
    
    private void cargarVentana(String direccion){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(direccion));
            Scene escena1 = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(escena1);
            stage.setTitle("Añadir Empleado");
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
    }
    
    
    
}
