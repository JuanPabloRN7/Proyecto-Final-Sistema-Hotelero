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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jona-samy
 */
public class Frm_VentanaLoginController implements Initializable {
     private @FXML TextField txtusuario;
     private @FXML PasswordField  txtclave;
     private @FXML Button btnacceder;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /*
     @FXML
    private  void eventkey (KeyEvent event ){
        
        Object evt = event.getSource();
        if(evt.equals(txtusuario)){ 
            if (event.getCha ){
            
            
            }
            cargarVentana("/vista/Frm_VentanaGeneral.fxml");
    
        }
    }*/
   @FXML
    private void iniarsesion(ActionEvent event) {
     String usuario ="hotel";
     String clave = "123456";
    
    if(usuario.equals(txtusuario.getText()) && clave.equals(txtclave.getText())){
        cargarVentana("/vista/Frm_VentanaGeneral.fxml");
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        }
        
    }
    
   

    private void cargarVentana(String direccion) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(direccion));
            Scene escena1 = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(escena1);
            stage.setTitle("RESERVAS");
            stage.show();
           
        } catch (IOException e) {
            System.out.println("Problema" + e);
        }
    }
    
}
