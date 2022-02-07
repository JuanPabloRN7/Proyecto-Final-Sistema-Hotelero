/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jainer Pinta
 */
public class ConexionDB {
    Connection conexion;
    
    /**
     * Obtener la conexion con la base de datos
     * @return Retorna la conexion realizada con la base de datos
     */
    public Connection conectar() {
        //Cambiar las variables url, user y pwd
        String user = "Specter";
        String pwd = "dedsec8";   
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/sistemahotelero";
            conexion = DriverManager.getConnection(url, user, pwd);
            System.out.println("Conexión realizada correctamente");
        } catch (SQLException e) {
            System.out.println("error conectar: "+e);
        }
        return conexion;
    }
}
