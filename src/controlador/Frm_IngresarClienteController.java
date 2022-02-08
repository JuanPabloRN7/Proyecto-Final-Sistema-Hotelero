/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.daos.ClienteDao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lista.controlador.Lista;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author pablo
 */
public class Frm_IngresarClienteController implements Initializable {

    //public TextField txclientee;
    private Stage stage;
    public String test;
    private ClienteDao clienteDao = new ClienteDao();
    private PersonaController pc = new PersonaController();
    /**
     * Initializes the controller class.
     */
    private @FXML
    TextField txtNombres;
    private @FXML
    TextField txtApellidos;
    private @FXML
    TextField txtCedula;
    private @FXML
    TextField txtDireccion;
    private @FXML
    TextField txtTelefono;
    private @FXML
    VBox vBoxDatos;

    public @FXML
    TableView<Persona> tblClientes;
    private @FXML
    TableColumn<?, ?> columNombres;
    private @FXML
    TableColumn<?, ?> columApellidos;
    private @FXML
    TableColumn<?, ?> columCedula;
    private @FXML
    TableColumn<?, ?> columDireccion;
    private @FXML
    TableColumn<?, ?> columTelefono;

    private @FXML
    TextField txtBuscar;
    private @FXML
    ComboBox<?> cbxcriterio;
    @FXML
    public Button btnseleccionar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTabla();
        ObservableList listacbx = FXCollections.observableArrayList("Todos", "Apellidos", "Nombres");
        cbxcriterio.setItems(listacbx);
        btnseleccionar.setVisible(false);
    }

    private void cargarTabla() {
        Lista<Persona> lista = new Lista<>();
        lista = clienteDao.listar();
        ObservableList<Persona> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < lista.sizeLista(); i++) {
            listaFX.add(lista.consultarDatoPosicion(i));
        }
        ingresarElementosTabla(listaFX);
    }

    private void ingresarElementosTabla(ObservableList<Persona> listaFX) {
        columNombres.setCellValueFactory(new PropertyValueFactory("Nombres"));
        columApellidos.setCellValueFactory(new PropertyValueFactory("Apellidos"));
        columCedula.setCellValueFactory(new PropertyValueFactory("Cedula"));
        columDireccion.setCellValueFactory(new PropertyValueFactory("Direccion"));
        columTelefono.setCellValueFactory(new PropertyValueFactory("Telefono"));
        tblClientes.setItems(listaFX);
        //return tblClientes;
    }

    @FXML
    public void seleccionarCliente(ActionEvent event) {
        try {
            int fila = tblClientes.getSelectionModel().getSelectedIndex();
            if (fila >= 0) {
                test = tblClientes.getItems().get(fila).getNombres();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            } else {
                crearAlerta(Alert.AlertType.ERROR, "Error", "FILA", "Seleccione la fila");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void guardarCliente(ActionEvent event) {
        if (verificarCampos()) {
            clienteDao.getPersona().setNombres(txtNombres.getText());
            clienteDao.getPersona().setApellidos(txtApellidos.getText());
            clienteDao.getPersona().setCedula(txtCedula.getText());
            clienteDao.getPersona().setDireccion(txtDireccion.getText());
            clienteDao.getPersona().setTelefono(txtTelefono.getText());
            pc.setPersona(clienteDao.getPersona());
            if (pc.verificarCedula()) {
                if (clienteDao.guardar()) {
                    crearAlerta(Alert.AlertType.INFORMATION, "OK", "Datos guardados", "Los datos se han almacenado correctamente");
                    limpiar();
                } else {
                    crearAlerta(Alert.AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
                }
            } else {
                crearAlerta(Alert.AlertType.ERROR, "Error", "Cedula inválida", "El número de cedula ingresado no es válido");
            }
        } else {
            crearAlerta(Alert.AlertType.INFORMATION, "Error", "Campos vacios", "Rellene todos los campos para guardar al empleado");
        }
    }

    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private boolean verificarCampos() {
        if (txtApellidos.getText().trim().isEmpty() || txtApellidos.getText().trim().isBlank()
                || txtCedula.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private void limpiar() {
        clienteDao.setPersona(null);
        cargarTabla();
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }

    @FXML
    private void buscar() {
        int select = cbxcriterio.getSelectionModel().getSelectedIndex();
        System.out.println("index" + (int) cbxcriterio.getSelectionModel().getSelectedIndex());
        Lista<Persona> aux = new Lista();
        switch (select) {
            case 0:
                aux = clienteDao.listar();
                break;
            case 1:
                aux = clienteDao.buscarCliente(txtBuscar.getText(), 1);
                break;
            case 2:
                aux = clienteDao.buscarCliente(txtBuscar.getText(), 2);
                break;
            default:
                cargarTabla();
        }
        ObservableList<Persona> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < aux.sizeLista(); i++) {
            listaFX.add(aux.consultarDatoPosicion(i));
        }
        ingresarElementosTabla(listaFX);
        txtBuscar.setText("");
    }

    @FXML
    private void Apellidos(SortEvent<Persona> event) {
    }

}
