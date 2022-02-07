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
    
    /**
     * Setear class a la lista
     * @param clazz Class del tipo Lista.
     */
    public AdaptadorDaoEmpleado(Class<T> clazz){
        this.clazz = clazz;
        lista.setClazz(clazz);
    }
    
    /**
     * Guardar un empleado en la base de datos.
     * @param dato Objeto de tipo Empleado
     * @return Retorna True si los datos se han guardado correctamente.
     */
    @Override
    public boolean guardar(T dato) {
        Empleado empleado = (Empleado)dato;
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO empleados(ID,Nombres,Apellidos,FechaNacimiento,Telefono,Direccion,Cedula,IDEmpleado,Cargo,Autorizacion) VALUE(?,?,?,?,?,?,?,?,?,?)");
            ps.setLong(1, empleado.getIdPersona());
            ps.setString(2, empleado.getNombres());
            ps.setString(3, empleado.getApellidos());
            ps.setString(4, empleado.getFechaNacimiento().toString());
            ps.setString(5, empleado.getTelefono());
            ps.setString(6, empleado.getDireccion());
            ps.setString(7, empleado.getCedula());
            ps.setString(8, empleado.getIdentificacion());
            ps.setString(9, empleado.getRol().getCargo());
            ps.setBoolean(10, empleado.getRol().isAutorizacion());
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
    
    /**
     * Modifica los datos de un empleado en la base de datos
     * @param nombre Nombre del empleado
     * @param apellido Apellido del empleado
     * @param telefono Telefono del empleado
     * @param cargo Cargo del empleado
     * @param id ID de empleado que se modificarra
     * @return Retorna true si se modifica el empleado.
     */
    @Override
    public boolean modificar(String nombre, String apellido, String telefono, String cargo, String id) {
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("UPDATE empleados SET Cargo = '"+cargo+"', SET Telefono = '"+telefono+"', SET Nombres = '"+nombre+"', "
                    + "SET Apellidos = '"+apellido+"', SET Cargo = '"+cargo+"' WHERE IDEmpleado='"+id+"'");
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
    
    /**
     * Elimina un empleado de la base de datos.
     * @param id Id del empleado a eliminar
     * @return Retorna True si se ha eliminado el empleado.
     */
    public boolean eliminar(String id){
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("DELETE FROM empleados WHERE IDEmpleado='"+id+"'");
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
    
    /**
     * Obtiene los empleados de la base de datos
     * @return Retorna una lista de tipo Empleado.
     */
    @Override
    public Lista<T> listar() {
        Statement st = null;
        ResultSet rs = null;
        lista = new Lista<>();
        Connection conexion = conexionDB.conectar();  
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM empleados");
            while (rs.next()) {    
                Empleado empleado = new Empleado();
                empleado.setIdPersona(rs.getLong("ID"));
                empleado.setNombres(rs.getString("Nombres"));
                empleado.setApellidos(rs.getString("Apellidos"));
                empleado.setFechaNacimiento(LocalDate.parse(rs.getString("FechaNacimiento")));
                empleado.setTelefono(rs.getString("Telefono"));
                empleado.setDireccion(rs.getString("Direccion"));
                empleado.setIdentificacion(rs.getString("IDEmpleado"));
                empleado.setCedula(rs.getString("Cedula"));
                empleado.setIdentificacion(rs.getString("IDEmpleado"));
                empleado.getRol().setCargo(rs.getString("Cargo"));
                empleado.getRol().setAutorizacion(rs.getBoolean("Autorizacion"));
                lista.insertarNodo((T)empleado);
            }            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean modificar(String dato, String ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
