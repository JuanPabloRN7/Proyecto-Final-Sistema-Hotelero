/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.daos.ReservaDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lista.controlador.Lista;
import modelo.Persona;
import modelo.Reservacion;

/**
 * FXML Controller class
 *
 * @author pablo
 */
public class Frm_IRController implements Initializable {

    public ReservaDao rd = new ReservaDao();
    private Stage stage;
    private Frm_IngresarClienteController iccontroller;
   
    private @FXML TextField txtCliente;
    @FXML
    private TextField txtHabitacion;
    private @FXML TextField txtCosto;
    private @FXML TableView<Reservacion> tblTabla;
    private @FXML TableColumn<?, ?> colCliente;
    private @FXML TableColumn<?, ?> colHabitacion;
    private @FXML TableColumn<?, ?> colFechaReserva;
    private @FXML TableColumn<?, ?> colCosto;
    private @FXML Button btnClente;
    private @FXML Button btnHabitacion;
    private @FXML DatePicker jdFechaReserva;
    private @FXML DatePicker jdfechaEntrada;
    private @FXML DatePicker jdFechaSalida;
    private @FXML TableColumn<?, ?> colFechaEntrada;
    private @FXML TableColumn<?, ?> colFechaSalida;
    private @FXML ComboBox<?> cbxservicios;
    private @FXML Button btnReserva;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        cargarTabla();
        ObservableList listacbx = FXCollections.observableArrayList("Gimnasio", "Piscina" );
        cbxservicios.setItems(listacbx);
    }
    
        private void cargarTabla() {
        Lista<Reservacion> lista = new Lista<>();
        lista = rd.listar();
        ObservableList<Reservacion> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < lista.sizeLista(); i++) {
            listaFX.add(lista.consultarDatoPosicion(i));
        }
        ingresarElementosTabla(listaFX);
    }

    private void ingresarElementosTabla(ObservableList<Reservacion> listaFX) {
        colCliente.setCellValueFactory(new PropertyValueFactory("id_persona"));
        colHabitacion.setCellValueFactory(new PropertyValueFactory("id_habitacion"));
        colFechaReserva.setCellValueFactory(new PropertyValueFactory("fecha"));
        colFechaEntrada.setCellValueFactory(new PropertyValueFactory("fecha_entrada"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory("fecha_salida"));
        colCosto.setCellValueFactory(new PropertyValueFactory("costoTotal"));
        tblTabla.setItems(listaFX);
    }

    @FXML
    private void guardarReserva(ActionEvent event) {
        if (verificarCampos()) {
            rd.getReserva().setId_habitacion(new Long(txtHabitacion.getText()));
            rd.getReserva().setId_persona(iccontroller.clienteDao.listar().consultarDatoPosicion(iccontroller.select).getIdPersona());
            rd.getReserva().setFecha(jdFechaReserva.getValue());
            rd.getReserva().setFecha_entrada(jdfechaEntrada.getValue());
            rd.getReserva().setFecha_salida(jdFechaSalida.getValue());
            rd.getReserva().setCostoTotal(Double.parseDouble(txtCosto.getText()));
            if (rd.guardar()) {
                crearAlerta(Alert.AlertType.INFORMATION, "OK", "Datos guardados", "Los datos se han almacenado correctamente");
                limpiar();
            } else {
                crearAlerta(Alert.AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
            }

        } else {
            crearAlerta(Alert.AlertType.INFORMATION, "Error", "Campos vacios", "Rellene todos los campos para guardar al empleado");
        }
    }
    
    private boolean verificarCampos() {
        if (txtCliente.getText().trim().isEmpty() || txtHabitacion.getText().trim().isBlank()
                || txtCosto.getText().trim().isEmpty() || jdFechaReserva.getValue().toString().equals("")
                || jdFechaSalida.getValue().toString().equals("") || jdfechaEntrada.getValue().toString().equals("")) {
            return false;
        }
        return true;
    }
    
      private void limpiar() {
          txtCliente.setText("");
          txtCosto.setText("");
          txtHabitacion.setText("");
    }

    @FXML
    private void seleccionarCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Frm_IngresarCliente.fxml"));
            loader.setLocation(getClass().getResource("/vista/Frm_IngresarCliente.fxml"));
            Parent root = loader.load();
            Frm_IngresarClienteController contro = loader.getController();
            this.iccontroller = contro;
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            contro.btnseleccionar.setVisible(true);
            stage.showAndWait();
            this.txtCliente.setText(contro.clienteDao.listar().consultarDatoPosicion(contro.select).getApellidos() + " " + contro.clienteDao.listar().consultarDatoPosicion(contro.select).getNombres());
        } catch (IOException ex) {
            Logger.getLogger(Frm_IRController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void seleccionarHabitacion(ActionEvent event) {
        
    }
    
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    
}
