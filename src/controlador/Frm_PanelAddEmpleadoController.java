/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Controlador.ConexionDB;
import controlador.daos.EmpleadoDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.enums.TipoEmpleado;

/**
 * FXML Controller class
 *
 * @author Jainer Pinta
 */
public class Frm_PanelAddEmpleadoController implements Initializable {
    private ConexionDB conexionDB = new ConexionDB();
    private EmpleadoDao empleadoDao = new EmpleadoDao();
    private PersonaController pc = new PersonaController();
    private EmpleadoController ec = new EmpleadoController();
    
    private @FXML TextField txtNombres;
    private @FXML TextField txtApellidos;
    private @FXML TextField txtTelefono;
    private @FXML TextField txtDireccion;
    private @FXML TextField txtCedula;
    private @FXML TextField txtID;
    private @FXML DatePicker txtFecha;
    private @FXML ComboBox cbxCargo;
    private @FXML VBox vboxDatos;
    private @FXML VBox vboxEmpleado;
    private @FXML HBox hboxEmpleado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCbxCargo();
    }
    
    private void cargarCbxCargo(){
        cbxCargo.getItems().clear();
        for (TipoEmpleado empleado : TipoEmpleado.values()) {
            cbxCargo.getItems().add(empleado.toString());
        }
    }
    
    public void modoEmpleado(boolean modo){
        if (!modo) {
            hboxEmpleado.setVisible(false);
        }
    }
    
    @FXML
    private void addEmpleado(){
        if (verificarCampos()) { 
            empleadoDao.getEmpleado().setApellidos(txtApellidos.getText());
            empleadoDao.getEmpleado().setNombres(txtNombres.getText());
            empleadoDao.getEmpleado().setTelefono(txtTelefono.getText());
            empleadoDao.getEmpleado().setDireccion(txtDireccion.getText());
            empleadoDao.getEmpleado().setCedula(txtCedula.getText());
            empleadoDao.getEmpleado().setFechaNacimiento(txtFecha.getValue());
            empleadoDao.getEmpleado().setIdentificacion(txtID.getText());
            empleadoDao.getEmpleado().getRol().setCargo(cbxCargo.getSelectionModel().getSelectedItem().toString());
            empleadoDao.getEmpleado().getRol().setAutorizacion(ec.asignarAutorizacion(cbxCargo.getSelectionModel().getSelectedItem().toString()));
            pc.setPersona(empleadoDao.getEmpleado());
            if (pc.verificarCedula()) {
                if (empleadoDao.guardar()) {
                    crearAlerta(AlertType.INFORMATION, "OK", "Datos guardados", "Los datos se han almacenado correctamente");
                }else{
                    crearAlerta(AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
                }
            }else{
                crearAlerta(AlertType.ERROR, "Error", "Cedula inválida", "El número de cedula ingresado no es válido");
            }
        }else{
            crearAlerta(AlertType.INFORMATION, "Error", "Campos vacios", "Rellene todos los campos para guardar al empleado");
        }
    }

    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    private boolean verificarCampos(){
        boolean camposTexto = true;
        for (Node nodo : vboxDatos.getChildren()) {
            if (nodo instanceof TextField) {
                if (((TextField)nodo).getText().trim().isEmpty()) {
                    camposTexto = false;
                }
            }
        }
        if (camposTexto && txtFecha.getValue() != null) {
            System.out.println("Paso");
            return true;
        }
        System.out.println("No paso");
        return false;
    }
    
}
