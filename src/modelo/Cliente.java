/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import modelo.Persona;

/**
 *
 * @author jona-samy
 */
public class Cliente  extends Persona{
    
    
    private String hora_entrada;
    private String nrohabitacion;
    private LocalDate Fecha_final;

   
    public LocalDate getFecha_final() {
        return Fecha_final;
    }

    public void setFecha_final(LocalDate Fecha_final) {
        this.Fecha_final = Fecha_final;
    }
    
    public String getNrohabitacion() {
        return nrohabitacion;
    }

    public void setNrohabitacion(String nrohabitacion) {
        this.nrohabitacion = nrohabitacion;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    
}
