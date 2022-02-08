/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Persona;

/**
 *
 * @author Jainer Pinta
 */
public class PersonaController {
    private Persona persona;

    /**
     * Obtiene el dato Persona
     * @return Rertorna un objeto de tipo Persona.
     */
    public Persona getPersona() {
        if (this.persona == null) {
            this.persona = new Persona();
        }
        return persona;
    }

    /**
     * Setear objeto tipo de Persona
     * @param persona Objeto tipo Persona.
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    /**
     * Algoritmo para verificar el número de cedula
     * @return Retorna True si la cedula es valida. 
     */
    public boolean verificarCedula(){

        String cedula = persona.getCedula();
        int sumaTotal = 0;
        for (int i = 0; i < cedula.length() - 1; i++) {
            if ((i + 1) % 2 == 0) {
                sumaTotal += Character.getNumericValue(cedula.charAt(i));
            } else {
                int num = Character.getNumericValue(cedula.charAt(i)) * 2;
                if (num > 9) {
                    num = num - 9;
                }
                sumaTotal += num;
            }
        }
        if (10 - (sumaTotal % 10) == Character.getNumericValue(cedula.charAt(cedula.length() - 1))) {
            return true;
        }
        return false;
    }
}
