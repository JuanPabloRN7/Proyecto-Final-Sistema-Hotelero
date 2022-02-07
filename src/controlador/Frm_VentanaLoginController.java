/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import controlador.daos.CuentaDao;
import controlador.daos.EmpleadoDao;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lista.controlador.Lista;
import modelo.Cuenta;
import modelo.Empleado;

/**
 * FXML Controller class
 *
 * @author jona-samy
 */
public class Frm_VentanaLoginController implements Initializable {
    private EmpleadoDao ed = new EmpleadoDao();
    private CuentaDao cd = new CuentaDao();
    private Lista<Empleado> empleados;
    private Lista<Cuenta> cuentas;
    
    private @FXML TextField txtusuario;
    private @FXML PasswordField  txtclave;
    private @FXML Button btnacceder;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listarCuentas();
    }    

    private void iniciarsesion(String cargo) {
        cargarVentana(cargo);
        Stage stage = (Stage) btnacceder.getScene().getWindow();
        stage.close();      
    }
    
    private void listarCuentas(){
        empleados = ed.listar();
        cuentas = cd.listar();
        empleados.setClazz(Empleado.class);
        cuentas.setClazz(Cuenta.class);
        empleados.quicksort(0, empleados.sizeLista()-1, "identificacion", Lista.ASCENDENTE);
        cuentas.quicksort(0, cuentas.sizeLista()-1,"identificaion", Lista.ASCENDENTE);
    }
    
    @FXML
    private void obtenerCuenta(){
        if(!verificarCampos()){
            Cuenta cuenta = cuentas.busquedaBinaria(txtusuario.getText().trim(), "identificacion");
            if(cuenta != null){
                if(validarCredenciales(cuenta.getIdentificacion(), cuenta.getClave())){
                    if(autorizar(cuenta.getIdentificacion())){
                        iniciarsesion(empleados.busquedaBinaria(cuenta.getIdentificacion(), "identificacion").getRol().getCargo());
                    }else{
                        crearAlerta(Alert.AlertType.ERROR, "Autorizacion", "No autorizado", "El empleado vinculado a la cuenta no se encuentra autorizado para ingresar al sistema");
                    }
                }else{
                   crearAlerta(Alert.AlertType.ERROR, "Error", "Datos incorrectos", "El dato ingresado en el campo usuario o contraseña no es correcto"); 
                }
            }else{
                crearAlerta(Alert.AlertType.ERROR, "Error", "Cuenta no existente", "Los datos ingresados no corresponden a una cuenta");
            }
        }else{
            crearAlerta(Alert.AlertType.ERROR, "Error", "Campos vacios", "Llene todos los campos");
        }
    }
    
    private boolean validarCredenciales(String identifiacion, String clave){
        return (identifiacion.equals(txtusuario.getText().trim()) && clave.equals(txtclave.getText()));
    }
    
    private boolean autorizar(String identificacion){
        Empleado empleado = empleados.busquedaBinaria(identificacion, "identificacion");
        return empleado.getRol().isAutorizacion();
    }
    
    private boolean verificarCampos(){
        return (txtusuario.getText().isEmpty() && txtclave.getText().isEmpty());
    }
    
    private void cargarVentana(String cargo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Frm_VentanaGeneral.fxml"));
            Parent root = loader.load();
            Frm_VentanaGeneralController ventana = loader.getController();
            if(cargo.equalsIgnoreCase("RECEPCIONISTA")){
                ventana.ocultarMA();   
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("SISTEMA HOTELERO");
            stage.show();
        } catch (IOException e) {
            System.out.println("Problema" + e);
        }
    }
    
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
}
