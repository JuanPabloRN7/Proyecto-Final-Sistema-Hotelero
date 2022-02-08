
package controlador.daos;

import Controlador.ConexionDB;
import modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import lista.controlador.Lista;
import modelo.Persona;

/**
 *
 * @author Jainer Pinta
 */
public class AdaptadorDaoClienterec<T> implements InterfazDao<T>{
    private Class<T> clazz;
    private ConexionDB conexionDB = new ConexionDB();
    private Lista<T> lista = new Lista<>();
    
    public AdaptadorDaoClienterec(Class<T> clazz){
        this.clazz = clazz;
        lista.setClazz(clazz);
    }
    
    @Override
    public boolean guardar(T dato) {
        Cliente cliente = (Cliente)dato;
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO recepcionclientes(ID,Nombres,Apellidos,Cedula,Telefono,Direccion,FechaEntrada,FechaSalida,HoraEntrada,HoraSalida,NroHabitacion) VALUE(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setLong(1, cliente.getIdPersona());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getCedula());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getDireccion()); 
            ps.setString(7, cliente.getFecha_inicio().toString());
            ps.setString(8, cliente.getFecha_final().toString());
            ps.setString(9, cliente.getHora_entrada());
            ps.setString(10, cliente.getHora_estimada());
            ps.setString(11, cliente.getNrohabitacion());
            //ps.setString(8, empleado.getIdentificacion());
            //ps.setString(9, empleado.getCargo());
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
    public boolean modificar(T dato, int ID) {
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("UPDATE recepcionclientes SET Cargo = '"+dato+"' WHERE IDEmpleado='"+ID+"'");
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
       
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM recepcionclientes");
            while (rs.next()) {       
                Cliente cliente = new Cliente();
                cliente.setIdPersona(rs.getLong("ID"));
                cliente.setNombres(rs.getString("Nombres"));
                cliente.setApellidos(rs.getString("Apellidos"));
                cliente.setCedula(rs.getString("Cedula"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setFecha_inicio(LocalDate.parse(rs.getString("FechaEntrada")));
                cliente.setFecha_final(LocalDate.parse(rs.getString("FechaSalida")));
                cliente.setHora_entrada(rs.getString("HoraEntrada"));
                cliente.setHora_estimada(rs.getString("HoraSalida"));
                cliente.setNrohabitacion(rs.getString("NroHabitacion"));
                lista.insertarNodo((T) cliente);
            }            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
