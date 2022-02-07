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
    private @FXML Button btnReporte;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Carga la ventana correspondiente al boton presionado.
     * @param e Evento de tipo ActionEvent.
     */
    @FXML
    private void seleccionarVentana(ActionEvent e){
        if (e.getSource() == btnReporte) {
            cargarVentana("/vista/Frm_ReporteEmpleados.fxml");
        }else{
            cargarVentana("/vista/Frm_AsignarCargo.fxml");
        }
    }
    
    /**
     * Carga una ventana FXML
     * @param direccion Directorio del archivo FXML.
     */
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
