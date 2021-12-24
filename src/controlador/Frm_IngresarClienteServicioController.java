/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Controlador.ConexionDB;
import controlador.daos.ClienteServiciosDao;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lista.controlador.Lista;
import modelo.ServiciosCliente;
/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class Frm_IngresarClienteServicioController implements Initializable {

    private ConexionDB conexionDB = new ConexionDB();
    Connection con = conexionDB.conectar();
    private ClienteServiciosDao csdao = new ClienteServiciosDao();
    
    @FXML
    private TextField txcliente;
    @FXML
    private TextField txPrecio;
    @FXML
    private ComboBox<String> cbServicio;
    @FXML
    private Button btIngresar;
    @FXML
    private TableView <ServiciosCliente> TablaSer;
    @FXML
    private TableColumn<?, ?> coId;
    @FXML
    private TableColumn<?, ?> coCliente;
    @FXML
    private TableColumn<?, ?> coServicio;
    @FXML
    private TableColumn<?, ?> coUso;
    @FXML
    private TableColumn<?, ?> coPrecio;
    @FXML
    private ComboBox<Double> cbUso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostarServicios();
        mostarUso();
        limpiar();
    }    

    @FXML
    private void Ingresar(ActionEvent event) {
        if(validar()){
            csdao.getServicios().setCliente(txcliente.getText());
            csdao.getServicios().setNombreServicio(cbServicio.getSelectionModel().getSelectedItem());
            csdao.getServicios().setUso(cbUso.getSelectionModel().getSelectedItem().intValue());
            csdao.getServicios().setPrecio(calcularPrecio());
            crearAlerta(AlertType.INFORMATION, "OK", "Precio", "Usted pagara por su servicio: "+calcularPrecio()+" dolares");
            if(csdao.guardar()){
                crearAlerta(AlertType.INFORMATION, "OK", "Datos Guardados", "Los datos han sido guardados");
                limpiar();
            }else{
                crearAlerta(AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
            }
        }else{
            crearAlerta(AlertType.ERROR, "Error", "Vacio", "Campos Vacios");
        }
    }

    @FXML
    private void ActionServicio(ActionEvent event) {
        mostarPrecio();
    }
    
    
    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    private boolean validar(){
        return txcliente.getText().trim().length()>0 && txPrecio.getText().trim().length()>0 && !cbServicio.getSelectionModel().isEmpty() && !cbUso.getSelectionModel().isEmpty(); 
    }
    
    private void limpiar(){
        csdao.setServicios(null);
        cargarTabla();
        txcliente.setText("");
        txPrecio.setText("");
    }
    
    private void cargarTabla(){
        Lista<ServiciosCliente> lista = new Lista<>();
        lista = csdao.listar();
        ObservableList<ServiciosCliente> listaFX = FXCollections.observableArrayList();
        for (int i = 0; i < lista.sizeLista(); i++) {
            listaFX.add(lista.consultarDatoPosicion(i));
        }
        coId.setCellValueFactory(new PropertyValueFactory("IdServicio"));
        coCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        coServicio.setCellValueFactory(new PropertyValueFactory("nombreServicio"));
        coPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        coUso.setCellValueFactory(new PropertyValueFactory("uso"));
        TablaSer.setItems(listaFX);
    }
    
    private void mostarServicios(){
        String sql = "SELECT servicios.Tipo FROM `servicios`";
        try {
            Statement st = con.createStatement();
            ResultSet rt = st.executeQuery(sql);
            while(rt.next()){
                int conti = 1;
                cbServicio.getItems().add(rt.getString(conti));
                conti++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    private void mostarPrecio(){
        String sql = "SELECT servicios.Tipo FROM `servicios`";
        String sql2 = "SELECT servicios.Tipo,servicios.Precio FROM `servicios`";
        try {
            Statement st = con.createStatement();
            ResultSet rt = st.executeQuery(sql);
            Statement st1 = con.createStatement();
            ResultSet rt2 = st1.executeQuery(sql2);
            while (rt.next() && rt2.next()) {                
               if(cbServicio.getSelectionModel().getSelectedItem().equals(rt.getString("Tipo"))){
                   txPrecio.setText(rt2.getString("Precio"));
               }
            }
        } catch (SQLException e) {
            System.out.println("Hubo un error: " +e);
        }
    }
    
    private void mostarUso(){
        Double arreglo[] = {1.0,2.0,3.0,4.0,5.0,6.0,7.0};
        cbUso.getItems().addAll(arreglo);
    }
    
    private double calcularPrecio(){
        return cbUso.getSelectionModel().getSelectedItem()*Double.parseDouble(txPrecio.getText());
    }


    
}
