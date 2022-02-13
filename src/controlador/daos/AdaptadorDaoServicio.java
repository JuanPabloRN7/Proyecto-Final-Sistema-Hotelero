/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import Controlador.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import lista.controlador.Lista;
import modelo.Servicio;

/**
 *
 * @author Usuario
 */
public class AdaptadorDaoServicio<T> implements InterfazDao<T>{

    private Class <T> clazz;
    private ConexionDB conexionDB = new ConexionDB();
    Connection conexion = conexionDB.conectar();
    private Lista<T> lista = new Lista<>();
    
    public AdaptadorDaoServicio(Class<T> clazz){
        this.clazz = clazz;
        lista.setClazz(clazz);
    }
    
    /**
     * Guarda los datos del Objeto en la base de datos
     * @param dato
     * @return true si se ha guardado correctamente
     */
    @Override
    public boolean guardar(T dato) {
        Servicio servicio = (Servicio) dato;
        try {
            PreparedStatement pst;
            pst = conexion.prepareStatement("INSERT INTO servicios (Tipo,Precio) values (?,?)");
            pst.setString(1, servicio.getNombreServicio());
            pst.setDouble(2, servicio.getPrecio());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error en el guardado de la base "+e);
            return false;
        }
    }

    /**
     * Modifica los datos de la base de datos grcias a una llave ID
     * @param dato
     * @param ID
     * @return true si se ha guardado correctamente
     */
    @Override
    public boolean modificar(T dato, int ID) {
        Servicio servicio = (Servicio) dato;
        try {
            PreparedStatement pst;
            pst = conexion.prepareStatement("UPDATE servicios SET Tipo = '"+servicio.getNombreServicio()+"', Precio = '"+servicio.getPrecio()+"' WHERE ID='"+ID+"'");
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error en el modificado de la base "+e);
            return false;
        }
    }

    /**
     * Guarda los datos de la base de datos en la lista de Servicios
     * @return Lista de Servicios 
     */
    @Override
    public Lista<T> listar() {
        Statement st = null;
        ResultSet rs = null;
        lista = new Lista<>();
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM servicios");
            while(rs.next()){
                Servicio servicioo = new Servicio();
                servicioo.setIdServicio(rs.getLong("ID"));
                servicioo.setNombreServicio(rs.getString("Tipo"));
                servicioo.setPrecio(rs.getDouble("Precio"));
                lista.insertarNodo((T) servicioo);
            }
            
        } catch (Exception e) {
            System.out.println("Error en listar "+e);
        }
        return lista;
    }
    
    @Override
    public boolean modificar(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(String dato, String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
