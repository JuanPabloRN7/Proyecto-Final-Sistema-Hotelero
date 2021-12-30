/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import controlador.daos.ClienteDao;
import Controlador.ConexionDB;
import controlador.daos.ClienterecDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author jona-samy
 */
public class Frm_IngresarCheckinController implements Initializable {

    private ConexionDB conexionDB = new ConexionDB();
    private ClienterecDao clienteDao = new ClienterecDao();
    private PersonaController pc = new PersonaController();

   private @FXML TextField txtNombres;
    private @FXML TextField txtApellidos;
    private @FXML TextField txtTelefono;
    private @FXML TextField txtDireccion;
    private @FXML TextField txtCedula;
    private @FXML DatePicker txtFechaEntrada;
    private @FXML DatePicker txtFechaSalida;
    private @FXML TextField txtHabitacion;
    private @FXML TextField txtHoraEntrada;
    private @FXML TextField txtHoraSalida;
    private @FXML VBox vboxDatos;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
     private void agregarCliente(){
        if (verificarCampos()) {
            clienteDao.getCliente().setApellidos(txtApellidos.getText());
            clienteDao.getCliente().setNombres(txtNombres.getText());
            clienteDao.getCliente().setTelefono(txtTelefono.getText());
            clienteDao.getCliente().setDireccion(txtDireccion.getText());
            clienteDao.getCliente().setCedula(txtCedula.getText());
            clienteDao.getCliente().setFecha_inicio(txtFechaEntrada.getValue());
            clienteDao.getCliente().setFecha_final(txtFechaSalida.getValue());
            clienteDao.getCliente().setHora_entrada(txtHoraEntrada.getText());
            clienteDao.getCliente().setHora_estimada(txtHoraSalida.getText());
            clienteDao.getCliente().setNrohabitacion(txtHabitacion.getText());
            //clienteDao.getCliente().setCargo("");
            pc.setPersona(clienteDao.getCliente());
            if (pc.verificarCedula()) {
                if (clienteDao.guardar()) {
                    crearAlerta(Alert.AlertType.INFORMATION, "OK", "Datos guardados", "Los datos se han almacenado correctamente");
                }else{
                    crearAlerta(Alert.AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
                }
            }else{
                crearAlerta(Alert.AlertType.ERROR, "Error", "Cedula inválida", "El número de cedula ingresado no es válido");
            }
        }else{
            crearAlerta(Alert.AlertType.INFORMATION, "Error", "Campos vacios", "Rellene todos los campos para guardar al empleado");
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
        if (camposTexto && txtFechaEntrada.getValue() != null) {
            System.out.println("Paso");
            return true;
        }
        System.out.println("No paso");
        return false;
    }
    
    
    
}
