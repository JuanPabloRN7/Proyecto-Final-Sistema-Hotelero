/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author pablo
 */
public class Reservacion {
    private Long id_reservacion;
    private Long id_persona;
    private Long id_detalleFactura;
    private Long id_habitacion;
    private LocalDate fecha;
    private LocalDate fecha_entrada;
    private LocalDate fecha_salida;
    private Double costoTotal;

    public Long getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(Long id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public Long getId_persona() {
        return id_persona;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    public Long getId_detalleFactura() {
        return id_detalleFactura;
    }

    public void setId_detalleFactura(Long id_detalleFactura) {
        this.id_detalleFactura = id_detalleFactura;
    }

    public Long getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(Long id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(LocalDate fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public LocalDate getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDate fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    

}
