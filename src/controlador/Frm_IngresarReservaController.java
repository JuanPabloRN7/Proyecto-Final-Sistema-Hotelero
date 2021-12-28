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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Reservacion;

/**
 * FXML Controller class
 *
 * @author pablo
 */
public class Frm_IngresarReservaController implements Initializable {

    private @FXML TextField txtCliente;
    private @FXML TextField txtHabitacion;
    private @FXML DatePicker txtFechaEntrada;
    private @FXML DatePicker txtFechaSalida;
    private @FXML DatePicker txtFechaReserva;
    private @FXML TextField txtCosto;
    private @FXML TableView<Reservacion> tblReservas;
    private @FXML TableColumn<?, ?> columnCliente;
    private @FXML TableColumn<?, ?> columHabitacion;
    private @FXML TableColumn<?, ?> columFechaReserva;
    private @FXML TableColumn<?, ?> columFechaEntrada;
    private @FXML TableColumn<?, ?> columFechaSalida;
    private @FXML TableColumn<?, ?> columCosto;
    private @FXML Button btnCliente;
    private @FXML Button btnHabitacion;
    private @FXML Button btnServicio;
    private @FXML TextField txtServicio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void cargarTabla() {

    }

    @FXML
    private void seleccionarVentana(ActionEvent event) {
        if (event.getSource() == btnCliente) {
            cargarVentana("/vista/Frm_IngresarCliente.fxml");
        }else if (event.getSource() == btnHabitacion) {
//            cargarVentana("/vista/Frm_.fxml");
        } else {
            cargarVentana("/vista/Frm_IngresarServicio.fxml");
        }
    }

    private void cargarVentana(String direccion) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(direccion));
            Scene escena1 = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(escena1);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
