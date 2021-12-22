/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jainer Pinta
 */
public class Frm_PanelAddEmpleadoController implements Initializable {
    private @FXML TextField txtNombres;
    private @FXML TextField txtApellidos;
    private @FXML TextField txtTelefono;
    private @FXML TextField txtDireccion;
    private @FXML TextField txtCedula;
    private @FXML DatePicker txtFecha;
    private @FXML VBox vboxDatos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
