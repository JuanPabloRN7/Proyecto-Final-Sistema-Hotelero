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
import java.sql.SQLException;
import java.sql.Statement;
import lista.controlador.Lista;
import modelo.Cuenta;

/**
 *
 * @author Jainer Pinta
 */
public class AdaptadorDaoCuenta<T> implements InterfazDao<T>{
    private Class<T> clazz;
    private ConexionDB conexionDB = new ConexionDB();
    private Lista<T> lista = new Lista<>();
    
    /**
     * Guarda la cuenta en la base de datos
     * @param dato Objeto de tipo Cuenta.
     * @return Retorna True si la cuenta se a registrado.
     */
    @Override
    public boolean guardar(T dato) {
        Cuenta cuenta = (Cuenta)dato;
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO cuentas(id,identificacion,clave) VALUE(?,?,?)");
            ps.setLong(1, cuenta.getId());
            ps.setString(2, cuenta.getIdentificacion());
            ps.setString(3, cuenta.getClave());
            int verificacion = ps.executeUpdate();
            ps.close();
            if (verificacion>0) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public boolean modificar(String dato, String ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(String nombre, String apellido, String telefono, String cargo, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obtiene todas las cuentas de la base de datos.
     * @return Lista de Tipo Cuenta.
     */
    @Override
    public Lista<T> listar() {
        Statement st = null;
        ResultSet rs = null;
        lista = new Lista<>();
        Connection conexion = conexionDB.conectar();  
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM cuentas");
            while (rs.next()) {    
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getLong("id"));
                cuenta.setIdentificacion(rs.getString("identificacion"));
                cuenta.setClave(rs.getString("clave"));
                lista.insertarNodo((T)cuenta);
            }            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
}
