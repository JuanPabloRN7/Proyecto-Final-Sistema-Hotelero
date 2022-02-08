/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Jainer Pinta
 */
public class Persona {
    //probando
    private Long idPersona;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;
    private String cedula;
    private LocalDate fechaNacimiento;
    
    /**
     * Obtener id de la persona.
     * @return Long id de la persona.
     */
    public Long getIdPersona() {
        return idPersona;
    }
    
    /**
     * Setear el id unico de la persona.
     * @param idPersona Long id de la persona.
     */
    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }
    
    /**
     * Obtener nombres de la persona
     * @return String nombres de persona.
     */
    public String getNombres() {
        return nombres;
    }
    
    /**
     * Setear nombres de la persona.
     * @param nombres String nombres de persona.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    /**
     * Obtener apellidos de la persona.
     * @return String apellidos de la persona.
     */
    public String getApellidos() {
        return apellidos;
    }
    
    /**
     * Setear apellidos de la persona
     * @param apellidos String apellidos de la persona.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    /**
     * Obtener fecha de nacimiento de la persona
     * @return LocalDate fecha de nacimiento.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    /**
     * Setear fecha de nacimiento
     * @param fechaNacimiento LocalDate fecha de nacimiento.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * Obtener telefono de persona.
     * @return String telefono de persona.
     */
    public String getTelefono() {
        return telefono;
    }
    
    /**
     * Setear telefono de persona
     * @param telefono String telefono de persona.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    /**
     * Obtener direccion de persona.
     * @return String direccion de persona.
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Setear direccion de persona.
     * @param direccion String direccion de persona.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    /**
     * Obtener cedula de persona.
     * @return String cedula de persona.
     */
    public String getCedula() {
        return cedula;
    }
    
    /**
     * Setear cedula de persona.
     * @param cedula String cedula de persona.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
}
