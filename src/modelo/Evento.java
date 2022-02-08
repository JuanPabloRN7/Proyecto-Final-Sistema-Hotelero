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
public class Evento extends Servicio{
    private String fecha;
    private String Jordana;
    private Double duracion;

    /**
     *  
     * @return Retorna la Fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * 
     * @param fecha 
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * 
     * @return String Jornada
     */
    public String getJordana() {
        return Jordana;
    }

    /**
     * 
     * @param Jordana 
     */
    public void setJordana(String Jordana) {
        this.Jordana = Jordana;
    }
    
    /**
     * 
     * @return La Duracion
     */
    public Double getDuracion() {
        return duracion;
    }

    /**
     * 
     * @param duracion 
     */
    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }
    
    
    
    
    
    
}
