/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Controlador.ConexionDB;
import controlador.daos.CuentaDao;
import controlador.daos.EmpleadoDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Empleado;
import modelo.enums.TipoEmpleado;

/**
 * FXML Controller class
 *
 * @author Jainer Pinta
 */
public class Frm_PanelAddEmpleadoController implements Initializable {
    private ConexionDB conexionDB = new ConexionDB();
    private EmpleadoDao empleadoDao = new EmpleadoDao();
    private CuentaDao cuentaDao = new CuentaDao();
    private PersonaController pc = new PersonaController();
    private EmpleadoController ec = new EmpleadoController();
    
    private @FXML TextField txtNombres;
    private @FXML TextField txtApellidos;
    private @FXML TextField txtTelefono;
    private @FXML TextField txtDireccion;
    private @FXML TextField txtCedula;
    private @FXML TextField txtID;
    private @FXML TextField txtClave;
    private @FXML DatePicker txtFecha;
    private @FXML ComboBox cbxCargo;
    private @FXML VBox vboxDatos;
    private @FXML VBox vboxEmpleado;
    private @FXML HBox hboxEmpleado;
    private @FXML Button btnAdd;
    private @FXML Button btnActualizar;
    private @FXML Button btnLimpiar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ec.setEmpleados(empleadoDao.listar());
        cargarCbxCargo();
        btnActualizar.setDisable(true);
        btnLimpiar.setDisable(true);
    }
    
    /**
     * Carga el combo box con los items del enum TipoEmpleado
     */
    private void cargarCbxCargo(){
        cbxCargo.getItems().clear();
        for (TipoEmpleado empleado : TipoEmpleado.values()) {
            cbxCargo.getItems().add(empleado.toString());
        }
    }
    
    /**
     * Oculta las opciones para registrar empleados
     * @param modo False para ocultar opciones de registro de empleados.
     */
    public void modoEmpleado(boolean modo){
        if (!modo) {
            hboxEmpleado.setVisible(false);
        }
    }
    
    /**
     * Añadir un empleado a la base de datos con los datos obtenidos de los campos.
     */
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
            if (pc.verificarCedula() && ec.verificarDisponibiladID(txtID.getText().trim())) {
                if (empleadoDao.guardar()) {
                    if(cbxCargo.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("RECEPCIONISTA") || cbxCargo.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("GERENTE")){
                        cuentaDao.getCuenta().setIdentificacion(txtID.getText().trim());
                        cuentaDao.getCuenta().setClave(txtClave.getText().trim());
                        cuentaDao.guardar();
                    }
                    crearAlerta(AlertType.INFORMATION, "OK", "Datos guardados", "Los datos se han almacenado correctamente");
                    empleadoDao.setEmpleado(null);
                    cuentaDao.setCuenta(null);
                    limpiarCampos();
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
    
    /**
     * Crea una alerta al usuario.
     * @param tipo Tipo de alerta.
     * @param titulo Titulo de la ventana alerta
     * @param cabecera Cabecera de la ventana alerta.
     * @param mensaje  Mensaje que se mostrara al usuario.
     */
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    /**
     * Verifica si los campos del formulario estan vacios.
     * @return Retorna False si los campos estan vacios-
     */
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
            return true;
        }
        return false;
    }
    
    public void cargarDatosEmpleado(Empleado empleado){
        txtNombres.setText(empleado.getNombres());
        txtApellidos.setText(empleado.getApellidos());
        txtFecha.setValue(empleado.getFechaNacimiento());
        txtDireccion.setText(empleado.getDireccion());
        txtID.setText(empleado.getIdentificacion());
        txtTelefono.setText(empleado.getTelefono());
        txtCedula.setText(empleado.getCedula());
        cbxCargo.setValue(empleado.getRol().getCargo());
        txtID.setDisable(true);
        btnAdd.setDisable(true);
        btnActualizar.setDisable(false);
        btnLimpiar.setDisable(false);
    }
    
    @FXML
    private void modificarEmpleado(){
        if(verificarCampos()){
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
            if(pc.verificarCedula()){
                if (empleadoDao.modificar()) {
                    if(cbxCargo.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("RECEPCIONISTA") || cbxCargo.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("GERENTE")){
                        cuentaDao.getCuenta().setIdentificacion(txtID.getText().trim());
                        cuentaDao.getCuenta().setClave(txtClave.getText().trim());
                        cuentaDao.modificar();
                    }
                    crearAlerta(AlertType.INFORMATION, "OK", "Datos actualizados", "Los datos se han actualizado correctamente");
                    empleadoDao.setEmpleado(null);
                    cuentaDao.setCuenta(null);
                    limpiarCampos();
                }else{
                    crearAlerta(AlertType.ERROR, "Error", "Datos no actualizados", "Ha surgido un error al actualizar los datos");
                }
            }else{
                crearAlerta(AlertType.ERROR, "Error", "Cedula inválida", "El número de cedula ingresado no es válido");
            }
        }else{
            crearAlerta(AlertType.INFORMATION, "Error", "Campos vacios", "Rellene todos los campos para actualizar al empleado");
        }
    }
    
    @FXML
    private void limpiarCampos(){
        boolean camposTexto = true;
        for (Node nodo : vboxDatos.getChildren()) {
            if (nodo instanceof TextField) {
                ((TextField)nodo).setText("");
            }
        }
        cbxCargo.setValue(null);
        txtFecha.setValue(null);
        txtID.setText("");
        txtClave.setText("");
        btnActualizar.setDisable(true);
        btnLimpiar.setDisable(true);
        btnAdd.setDisable(false);
    }
}
