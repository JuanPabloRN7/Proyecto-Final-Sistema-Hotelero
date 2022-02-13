/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.daos.ClienteDao;
import java.net.URL;

import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lista.controlador.Lista;
import modelo.Cliente;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author pablo
 */
public class Frm_IngresarClienteController implements Initializable {
    
    
    private Stage stage;
    public String test;
    ClienteDao clienteDao = new ClienteDao();
    private PersonaController pc = new PersonaController();
    
    private @FXML TextField txtseleccionar;
    private @FXML TextField txtNombres;
    private @FXML TextField txtApellidos;
    private @FXML TextField txtCedula;
    private @FXML TextField txtDireccion;
    private @FXML TextField txtTelefono;
    private @FXML TextField txthabitacion;
    private @FXML TextField txthoraingreso;
    private @FXML DatePicker datefechasalida;
    private @FXML VBox vBoxDatos;

    
    private @FXML TableView<Cliente> tblClientes;
    private @FXML TableColumn<?, ?> columNombres;
    private @FXML TableColumn<?, ?> columApellidos;
    private @FXML TableColumn<?, ?> columCedula;
    private @FXML TableColumn<?, ?> columDireccion;
    private @FXML TableColumn<?, ?> columTelefono;
    private @FXML TableColumn<?, ?> columHabitacion;
    private @FXML TableColumn<?, ?> columHoraEntrada;
    private @FXML TableColumn<?, ?> columFechaSalida;
    
    private @FXML TextField txtBuscar;
     @FXML Button btnseleccionar;
    private @FXML ComboBox<?> cbxcriterio;
     @FXML Button btnseleccionar2;
    int select;
    
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTabla();
        ObservableList listacbx = FXCollections.observableArrayList("Todos","Apellidos","Nombres");
        cbxcriterio.setItems(listacbx);
    }
    
    /**
     * carga la Tabla
     */
    private void cargarTabla(){
        Lista<Cliente> lista = new Lista<>();
        lista =clienteDao.listar1();
        ObservableList<Cliente> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < lista.sizeLista(); i++) {
            listaFX.add((Cliente)lista.consultarDatoPosicion(i));
        }
        ingresarElementosTabla(listaFX);
    }
    /**
     * Se ingresa los datos a la tabla
     * @param Listafx 
     */
    private void ingresarElementosTabla(ObservableList<Cliente> listaFX){
        columNombres.setCellValueFactory(new PropertyValueFactory("Nombres"));
        columApellidos.setCellValueFactory(new PropertyValueFactory("Apellidos"));
        columCedula.setCellValueFactory(new PropertyValueFactory("cedula"));
        columDireccion.setCellValueFactory(new PropertyValueFactory("Direccion"));
        columTelefono.setCellValueFactory(new PropertyValueFactory("Telefono"));
        columHabitacion.setCellValueFactory(new PropertyValueFactory("nrohabitacion"));
        columHoraEntrada.setCellValueFactory(new PropertyValueFactory("hora_entrada"));
        columFechaSalida.setCellValueFactory(new PropertyValueFactory("Fecha_final"));
        tblClientes.setItems(listaFX);
    }
    
    /**
     * 
     * Nos permite selccionar un cliente de la ventana reservas
     * @param event 
     */

    @FXML
    private void seleccionarCliente2(ActionEvent event) {
         try { 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Frm_IR.fxml")); 
            loader.setLocation(getClass().getResource("/vista/Frm_IR.fxml")); 
            Parent root = loader.load(); 
            Frm_IRController contro = loader.getController(); 
            Stage stage = new Stage(); 
            Scene scene = new Scene(root); 
            stage.setScene(scene); 
            //contro.btnseleccionar.setVisible(true); 
            stage.showAndWait(); 
            this.txtseleccionar.setText(contro.test); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        
    }
    /**
     * 
     * Nos permite selccionar un cliente de la ventana ingresar cliente
     * @param event 
     */
    
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
    /**
     *Guarda los datos en la base de datos 
     * @param event
     */

    @FXML
    private void guardarCliente(ActionEvent event) {
        if (verificarCampos()) { 
            clienteDao.getCliente().setNombres(txtNombres.getText());
            clienteDao.getCliente().setApellidos(txtApellidos.getText());
            clienteDao.getCliente().setCedula(txtCedula.getText());
            clienteDao.getCliente().setDireccion(txtDireccion.getText());
            clienteDao.getCliente().setTelefono(txtTelefono.getText());
            clienteDao.getCliente().setNrohabitacion(txthabitacion.getText());
            clienteDao.getCliente().setHora_entrada(txthoraingreso.getText());
            clienteDao.getCliente().setFecha_final(datefechasalida.getValue());
           
            
            
            pc.setPersona(clienteDao.getCliente());
            if (pc.verificarCedula()) {
                if (clienteDao.guardar()) {
                    crearAlerta(Alert.AlertType.INFORMATION, "OK", "Datos guardados", "Los datos se han almacenado correctamente");
                    limpiar();
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
    /**
     * Crea una alerta
     * @param tipo
     * @param titulo
     * @param cabecera
     * @param mensaje 
     */
    
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    /**
     * carga la Tabla
     */
    
    private boolean verificarCampos(){
        if(txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isBlank() 
           || txtCedula.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty() 
           || txtTelefono.getText().trim().isEmpty() || txthabitacion.getText().trim().isEmpty()
           || txthoraingreso.getText().trim().isEmpty()){
            return false;
        }
        return true;
    }
    /**
     * Limpia  los Textfields
     */
    private void limpiar(){
        clienteDao.setCliente(null);
        cargarTabla();
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txthabitacion.setText("");
        txthoraingreso.setText("");
        //datefechasalida.set;
    }
    /**
     * Busca un elemento en especifico 
     */
    @FXML
    private void buscar() {
        int select = cbxcriterio.getSelectionModel().getSelectedIndex();
        System.out.println("index" +(int)cbxcriterio.getSelectionModel().getSelectedIndex() );
        Lista<Cliente> aux = new Lista();
        switch (select) {
            case 0:
                aux = clienteDao.listar1();
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
        ObservableList<Cliente> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < aux.sizeLista(); i++) {
            listaFX.add((Cliente)aux.consultarDatoPosicion(i));
        }
        ingresarElementosTabla(listaFX);
        txtBuscar.setText("");
    }
    /**
     * hace que una columna se despliege
     *
     */
    @FXML
    private void Apellidos(SortEvent<Persona> event) {
        
    }


}
