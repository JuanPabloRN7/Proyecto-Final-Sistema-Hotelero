/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Servicio {
    private Long IdServicio;
    private String nombreServicio; 
    private double precio;

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getIdServicio() {
        return IdServicio;
    }

    public void setIdServicio(Long IdServicio) {
        this.IdServicio = IdServicio;
    }
    
    
}
