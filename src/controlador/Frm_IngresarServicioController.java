/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.daos.ServicioDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lista.controlador.Lista;
import modelo.Servicio;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class Frm_IngresarServicioController implements Initializable {

    private int fila;
    private int cont = 0;
    private ServicioDao serviciodao = new ServicioDao();

    @FXML
    private TableView<Servicio> Tabla;
    @FXML
    private TextField txServicio;
    @FXML
    private TextField txPrecio;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colServicio;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private Button btnmodificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        limpiar();
    }

    /**
     * Carga la Tabla
     */
    private void cargarTabla() {
        Lista<Servicio> lista = new Lista<>();
        lista = serviciodao.listar();
        ObservableList<Servicio> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < lista.sizeLista(); i++) {
            listaFX.add(lista.consultarDatoPosicion(i));
        }
        colID.setCellValueFactory(new PropertyValueFactory("IdServicio"));
        colServicio.setCellValueFactory(new PropertyValueFactory("nombreServicio"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        Tabla.setItems(listaFX);
        //Tabla.getItems().setAll(listaFX);
    }

    /**
     * Ingresa los servicios o Modificar segun sea el caso
     */
    @FXML
    private void ingresarServicio() {
        if (validar()) {
            serviciodao.getServicio().setNombreServicio(txServicio.getText());
            serviciodao.getServicio().setPrecio(Double.parseDouble(txPrecio.getText()));
            if (cont == 0) {
                if (serviciodao.guardar()) {
                    crearAlerta(AlertType.INFORMATION, "OK", "Datos Guardados", "Los datos han sido guardados");
                    limpiar();
                } else {
                    crearAlerta(AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
                }
            }else{
                if(serviciodao.modificar(serviciodao.getServicio(),Tabla.getItems().get(fila).getIdServicio().intValue())){
                    crearAlerta(AlertType.INFORMATION, "OK", "Datos Modificados", "Los datos han sido Modificados");
                    limpiar();
                    fila = 0;
                    cont = 0;
                }else{
                    crearAlerta(AlertType.ERROR, "Error", "Datos no modificados", "Ha surgido un error al modificar los datos");
                }
            }
        } else {
            crearAlerta(AlertType.ERROR, "Error", "Vacio", "Campos Vacios");
        }
    }

    /**
     * 
     * @param tipo
     * @param titulo
     * @param cabecera
     * @param mensaje 
     */
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Valida si los campos estan vacios
     * @return 
     */
    private boolean validar() {
        return txServicio.getText().trim().length() > 0 && txPrecio.getText().trim().length() > 0;
    }

    /**
     *  Limpia el Objeto y los Tx, y cara la Tabla
     */
    private void limpiar() {
        serviciodao.setServicio(null);
        cargarTabla();
        txServicio.setText("");
        txPrecio.setText("");
    }

    /**
     * Setea la fila seleccionada todos sus datos a los respectivos TextField
     * @param event 
     */
    @FXML
    private void modificarServicio(ActionEvent event) {
        fila = Tabla.getSelectionModel().getSelectedIndex();
        if (fila < 0) {
            crearAlerta(Alert.AlertType.ERROR, "Error", "FILA", "No ha seleccionado una fila");
        } else {
            txServicio.setText(Tabla.getItems().get(fila).getNombreServicio());
            txPrecio.setText(String.valueOf(Tabla.getItems().get(fila).getPrecio()));
        }
        if (event.getSource() == btnmodificar) {
            cont = 1;
        }
    }
}
