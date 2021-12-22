/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Jainer Pinta
 */
public class ConexionDB {
    Connection conexion;
    public Connection conectar(){
        String url = "jdbc:mariadb://localhost:3306/goodnight";
        String user = "Specter";
        String pwd = "dedsec8";     
        try {
            conexion = DriverManager.getConnection(url, user, pwd);
            System.out.println("Conexión realizada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
