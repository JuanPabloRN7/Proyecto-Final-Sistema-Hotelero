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

    /**
     * 
     * @return NombreServicio
     */
    public String getNombreServicio() {
        return nombreServicio;
    }

    /**
     * 
     * @param nombreServicio 
     */ 
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    /**
     * 
     * @return Precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * 
     * @param precio 
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * 
     * @return IdServicio
     */
    public Long getIdServicio() {
        return IdServicio;
    }

    /**
     * 
     * @param IdServicio 
     */
    public void setIdServicio(Long IdServicio) {
        this.IdServicio = IdServicio;
    }
    
    
}
