/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.daos.EmpleadoDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lista.controlador.Lista;
import modelo.Empleado;
import modelo.enums.TipoEmpleado;

/**
 * FXML Controller class
 *
 * @author Jainer Pinta
 */
public class Frm_AsignarCargoController implements Initializable {
    private EmpleadoDao empleadoDao = new EmpleadoDao();
    private @FXML TableView tblEmpleados;
    private @FXML TableColumn<Empleado, String> colID;
    private @FXML TableColumn<Empleado, String> colNombres;
    private @FXML TableColumn<Empleado, String> colApellidos;
    private @FXML TableColumn<Empleado, String> colCedula;
    private @FXML TableColumn<Empleado, String> colTelefono;
    private @FXML TableColumn<Empleado, String> colCargo;
    private @FXML Pane panelFormulario;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTabla();
        cargarFormulario();
    }    
    
    private void cargarFormulario(){
        try{
            Pane formulario = FXMLLoader.load(getClass().getResource("/vista/Frm_PanelAddEmpleado.fxml"));
            panelFormulario.getChildren().add(formulario);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    private void cargarTabla(){
        Lista<Empleado> lista = empleadoDao.listar();
        ObservableList<Empleado> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < lista.sizeLista(); i++) {
            listaFX.add(lista.consultarDatoPosicion(i));
        }
        colID.setCellValueFactory(new PropertyValueFactory<Empleado,String>("identificacion"));
        colNombres.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellidos"));
        colCedula.setCellValueFactory(new PropertyValueFactory<Empleado,String>("cedula"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Empleado,String>("telefono"));
        colCargo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRol().getCargo()));
        tblEmpleados.getItems().setAll(listaFX);           
    }
    
}
