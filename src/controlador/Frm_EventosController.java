/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class Frm_EventosController implements Initializable {

    @FXML
    private ComboBox<?> cbTipo;
    @FXML
    private ComboBox<?> cbJornada;
    @FXML
    private DatePicker txtFecha;
    @FXML
    private TableView<?> TablaEve;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colTipoEvento;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colJornada;
    @FXML
    private TableColumn<?, ?> colFecha;
    @FXML
    private TableColumn<?, ?> colDuracion;
    @FXML
    private TextField txPrecio;
    @FXML
    private TextField txcliente;
    @FXML
    private Button btnselect;
    @FXML
    private Button btIngresar;
    @FXML
    private ComboBox<?> cbInicio;
    @FXML
    private ComboBox<?> cbFin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txcliente.setEditable(false);
        ObservableList listacbx = FXCollections.observableArrayList("Celebracion", "Conferencia", "Evento Cultural", "Reunión");
        cbTipo.setItems(listacbx);
        listacbx = FXCollections.observableArrayList("Matutina", "Vespertina", "Nocturna");
        cbJornada.setItems(listacbx);
    }

    /**
     * Se agrega las horas a los combo box segun sea la jornada
     * @param event 
     */
    @FXML
    private void ActionJornada(ActionEvent event) {
        ObservableList listacbx;
        int select = cbJornada.getSelectionModel().getSelectedIndex();
        switch (select) {
            case 0:
                cbInicio.getItems().clear();
                cbFin.getItems().clear();
                listacbx = FXCollections.observableArrayList("8am", "9am", "10am", "11am", "12am");
                cbInicio.setItems(listacbx);
                cbFin.setItems(listacbx);
                break;
            case 1:
                cbInicio.getItems().clear();
                cbFin.getItems().clear();
                listacbx = FXCollections.observableArrayList("1pm", "2pm", "3pm", "4pm", "5pm", "6pm", "7pm");
                cbInicio.setItems(listacbx);
                cbFin.setItems(listacbx);
                break;
            case 2:
                cbInicio.getItems().clear();
                cbFin.getItems().clear();
                listacbx = FXCollections.observableArrayList("8pm", "9pm", "10pm", "11pm", "12pm", "1am", "2am");
                cbInicio.setItems(listacbx);
                cbFin.setItems(listacbx);
                break;
        }
    }

}
