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
import java.time.LocalDate;
import lista.controlador.Lista;
import modelo.Empleado;

/**
 *
 * @author Jainer Pinta
 */
public class AdaptadorDaoEmpleado<T> implements InterfazDao<T>{
    private Class<T> clazz;
    private ConexionDB conexionDB = new ConexionDB();
    private Lista<T> lista = new Lista<>();
    
    public AdaptadorDaoEmpleado(Class<T> clazz){
        this.clazz = clazz;
        lista.setClazz(clazz);
    }
    
    @Override
    public boolean guardar(T dato) {
        Empleado empleado = (Empleado)dato;
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO empleados(ID,Nombres,Apellidos,FechaNacimiento,Telefono,Direccion,Cedula,IDEmpleado,Cargo) VALUE(?,?,?,?,?,?,?,?,?)");
            ps.setLong(1, empleado.getIdPersona());
            ps.setString(2, empleado.getNombres());
            ps.setString(3, empleado.getApellidos());
            ps.setString(4, empleado.getFechaNacimiento().toString());
            ps.setString(5, empleado.getTelefono());
            ps.setString(6, empleado.getDireccion());
            ps.setString(7, empleado.getCedula());
            ps.setString(8, empleado.getIdentificacion());
            ps.setString(9, empleado.getCargo());
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
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("UPDATE empleados SET Cargo = '"+dato+"' WHERE IDEmpleado='"+ID+"'");
            int verificacion = ps.executeUpdate();
            ps.close();
            if (verificacion>0) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    @Override
    public Lista<T> listar() {
        Statement st = null;
        ResultSet rs = null;
        lista = new Lista<>();
        Connection conexion = conexionDB.conectar();  
        Empleado empleado = new Empleado();
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM empleados");
            while (rs.next()) {        
                empleado.setIdPersona(rs.getLong("ID"));
                empleado.setNombres(rs.getString("Nombres"));
                empleado.setApellidos(rs.getString("Apellidos"));
                empleado.setFechaNacimiento(LocalDate.parse(rs.getString("FechaNacimiento")));
                empleado.setTelefono(rs.getString("Telefono"));
                empleado.setDireccion(rs.getString("Direccion"));
                empleado.setIdentificacion(rs.getString("IDEmpleado"));
                empleado.setCedula(rs.getString("Cedula"));
                empleado.setIdentificacion(rs.getString("IDEmpleado"));
                empleado.setCargo(rs.getString("Cargo"));
                lista.insertarNodo((T) empleado);
            }            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
