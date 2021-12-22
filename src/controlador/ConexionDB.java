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
        //Cambiar las variables url, user y pwd
        String url = "jdbc:mariadb://localhost:3306/sistemahotelero";
        String user = "";
        String pwd = "";     
        try {
            conexion = DriverManager.getConnection(url, user, pwd);
            System.out.println("Conexión realizada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
