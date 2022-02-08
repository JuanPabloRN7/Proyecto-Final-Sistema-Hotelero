/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Controlador.ConexionDB;
import controlador.daos.ClienteServiciosDao;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lista.controlador.Lista;
import modelo.ServiciosCliente;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class Frm_IngresarClienteServicioController implements Initializable {

    private int fila;
    private int cont = 0;
    private ConexionDB conexionDB = new ConexionDB();
    Connection con = conexionDB.conectar();
    private ClienteServiciosDao csdao = new ClienteServiciosDao();

    @FXML
    public TextField txcliente;
    @FXML
    private TextField txPrecio;
    @FXML
    private ComboBox<String> cbServicio;
    @FXML
    private Button btIngresar;
    @FXML
    private Button btseleccionar;
    @FXML
    private TableView<ServiciosCliente> TablaSer;
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
    @FXML
    private TextField txbuscar;
    @FXML
    private Button btnbuscar;
    @FXML
    private ComboBox<?> cbbuscar;
    @FXML
    private Button btnmodificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostarServicios();
        txcliente.setEditable(false);
        ObservableList listacbx = FXCollections.observableArrayList("Todos", "Cliente", "Servicio");
        cbbuscar.setItems(listacbx);
        mostarUso();
        limpiar();
    }

    @FXML
    private void Ingresar(ActionEvent event) {
        if (validar()) {
            csdao.getServicios().setCliente(txcliente.getText());
            csdao.getServicios().setNombreServicio(cbServicio.getSelectionModel().getSelectedItem());
            csdao.getServicios().setUso(cbUso.getSelectionModel().getSelectedItem().intValue());
            csdao.getServicios().setPrecio(calcularPrecio());
            System.out.println(csdao.getServicios().getIdServicio());
            crearAlerta(AlertType.INFORMATION, "OK", "Precio", "Usted pagara por su servicio: " + calcularPrecio() + " dolares");
            if (cont == 0) {
                if (csdao.guardar()) {
                    crearAlerta(AlertType.INFORMATION, "OK", "Datos Guardados", "Los datos han sido guardados");
                    limpiar();
                } else {
                    crearAlerta(AlertType.ERROR, "Error", "Datos no guardados", "Ha surgido un error al guardar los datos");
                }
            }else{
                if(csdao.modificar(csdao.getServicios(),TablaSer.getItems().get(fila).getIdServicio().intValue())){
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

    @FXML
    private void ActionServicio(ActionEvent event) {
        mostarPrecio();
    }

    private void crearAlerta(Alert.AlertType tipo, String titulo, String cabecera, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private boolean validar() {
        return txcliente.getText() != null && txPrecio.getText().trim().length() > 0 && !cbServicio.getSelectionModel().isEmpty() && !cbUso.getSelectionModel().isEmpty();
    }

    private void limpiar() {
        csdao.setServicios(null);
        cargarTabla();
        txcliente.setText("");
        txPrecio.setText("");
    }

    private void cargarTabla() {
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

    @FXML
    public void seleccionarCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Frm_IngresarCliente.fxml"));
            loader.setLocation(getClass().getResource("/vista/Frm_IngresarCliente.fxml"));
            Parent root = loader.load();
            Frm_IngresarClienteController contro = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            contro.btnseleccionar.setVisible(true);
            stage.showAndWait();
            this.txcliente.setText(contro.test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostarServicios() {
        String sql = "SELECT servicios.Tipo FROM `servicios`";
        try {
            Statement st = con.createStatement();
            ResultSet rt = st.executeQuery(sql);
            while (rt.next()) {
                int conti = 1;
                cbServicio.getItems().add(rt.getString(conti));
                conti++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    private void mostarPrecio() {
        String sql = "SELECT servicios.Tipo FROM `servicios`";
        String sql2 = "SELECT servicios.Tipo,servicios.Precio FROM `servicios`";
        try {
            Statement st = con.createStatement();
            ResultSet rt = st.executeQuery(sql);
            Statement st1 = con.createStatement();
            ResultSet rt2 = st1.executeQuery(sql2);
            while (rt.next() && rt2.next()) {
                if (cbServicio.getSelectionModel().getSelectedItem().equals(rt.getString("Tipo"))) {
                    txPrecio.setText(rt2.getString("Precio"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Hubo un error: " + e);
        }
    }

    private void mostarUso() {
        Double arreglo[] = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0};
        cbUso.getItems().addAll(arreglo);
    }

    private double calcularPrecio() {
        return cbUso.getSelectionModel().getSelectedItem() * Double.parseDouble(txPrecio.getText());
    }

    public Stage cargarVentana(String direccion) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(direccion));
            Scene escena1 = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(escena1);
            stage.show();
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    public void buscar() {
        int select = cbbuscar.getSelectionModel().getSelectedIndex();
        if (txbuscar.getText().isEmpty() && select != 0) {
            crearAlerta(AlertType.ERROR, "Error", "Vacio", "Campos Vacios");
        } else {
            Lista<ServiciosCliente> aux = null;
            switch (select) {
                case 0:
                    aux = csdao.listar();
                    break;
                case 1:
                    aux = csdao.BusquedaServicios(txbuscar.getText(), select);
                    break;
                case 2:
                    aux = csdao.BusquedaServicios(txbuscar.getText(), select);
                    break;
            }
            if (aux.consultarDatoPosicion(0) == null) {
                crearAlerta(AlertType.ERROR, "Error", "No se Encuentra", "No se encontro un valor vuelva a buscar");
            } else {
                ObservableList<ServiciosCliente> listaFX = FXCollections.observableArrayList();
                for (int i = 0; i < aux.sizeLista(); i++) {
                    listaFX.add(aux.consultarDatoPosicion(i));
                }
                coId.setCellValueFactory(new PropertyValueFactory("IdServicio"));
                coCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
                coServicio.setCellValueFactory(new PropertyValueFactory("nombreServicio"));
                coPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
                coUso.setCellValueFactory(new PropertyValueFactory("uso"));
                TablaSer.setItems(listaFX);
                txbuscar.setText("");
            }
        }
    }

    private void modificarr() {

    }

    private void buscar(ActionEvent event) {
        buscar();
    }

    @FXML
    private void modificar(ActionEvent event) {
        fila = TablaSer.getSelectionModel().getSelectedIndex();
        if(fila<0){
            crearAlerta(Alert.AlertType.ERROR, "Error", "FILA", "No ha seleccionado una fila");
        }else{
            txcliente.setText(TablaSer.getItems().get(fila).getCliente());
            txPrecio.setText(String.valueOf(TablaSer.getItems().get(fila).getPrecio()));
        }
        if(event.getSource() == btnmodificar){
            cont = 1;
        }
        modificarr();
    }

}
