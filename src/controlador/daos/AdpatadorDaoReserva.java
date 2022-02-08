/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import Controlador.ConexionDB;
import java.net.InterfaceAddress;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import lista.controlador.Lista;
import modelo.Persona;
import modelo.Reservacion;

/**
 *
 * @author pablo
 */
public class AdpatadorDaoReserva<T> implements InterfazDao<T> {

    private Class<T> clazz;
    private ConexionDB conexionDB = new ConexionDB();
    private Lista<T> lista = new Lista<>();

    public AdpatadorDaoReserva(Class<T> clazz) {
        this.clazz = clazz;
        lista.setClazz(clazz);
    }

    @Override
    public boolean guardar(T dato) {
        Reservacion reserva = (Reservacion) dato;
        try {
            Connection conexion = conexionDB.conectar();
            PreparedStatement pst;
            pst = conexion.prepareStatement("INSERT INTO reservas (id_persona,id_habitacion,fecha_reserva,fecha_entrada,fecha_salida,costoTotal) values (?,?,?,?,?,?)");
            pst.setLong(1, reserva.getId_persona());
            pst.setLong(2, reserva.getId_habitacion());
            pst.setString(3, reserva.getFecha().toString());
            pst.setString(4, reserva.getFecha_entrada().toString());
            pst.setString(5, reserva.getFecha_salida().toString());
            pst.setDouble(6, reserva.getCostoTotal());
            int verificacion = pst.executeUpdate();
            pst.close();
            if (verificacion > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en el guardado de la base " + e);
            return false;
        }
    }

    @Override
    public boolean modificar(String dato, String ID) {
        return false;

    }

    @Override
    public Lista<T> listar() {
        Statement st = null;
        ResultSet rs = null;
        lista = new Lista<>();
        Connection conexion = conexionDB.conectar();
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM reservas");
            while (rs.next()) {
                Reservacion reserva = new Reservacion();
                reserva.setId_persona(rs.getLong("id_persona"));
                reserva.setId_habitacion(rs.getLong("id_habitacion"));
                reserva.setFecha(rs.getDate("fecha_reserva").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                reserva.setFecha_entrada(rs.getDate("fecha_entrada").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                reserva.setFecha_salida(rs.getDate("fecha_salida").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                reserva.setCostoTotal(rs.getDouble("costoTotal"));
                lista.insertarNodo((T) reserva);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean modificar(T dato, int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
