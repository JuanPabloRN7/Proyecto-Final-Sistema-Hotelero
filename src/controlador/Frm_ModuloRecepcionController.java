/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
 * @author jona-samy
 */
public class Frm_ModuloRecepcionController implements Initializable {
    
     private @FXML Button btncliente;
      private @FXML Button btnsalida;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    /**
     * Permite seleccionar  ventanas de salida y entrada de cliente
     *  @param e
     */
    
    @FXML
    private void seleccionarVentana(ActionEvent e){
        if (e.getSource() == btncliente) {
            cargarVentana("/vista/Frm_IngresarCliente.fxml");
        }else if (e.getSource() == btnsalida) {
            cargarVentana("/vista/Frm_SalidaCliente.fxml");
        }
        
    }
    /**
     * Permite abrir las ventanas
     *  @param direeccion 
     */
    
    private void cargarVentana(String direccion){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(direccion));
            Scene escena1 = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(escena1);
            stage.setTitle("Añadir CLiente");
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        } 
    }
    
}
