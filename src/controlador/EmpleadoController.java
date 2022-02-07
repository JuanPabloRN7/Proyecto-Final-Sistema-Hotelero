/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import lista.controlador.Lista;
import modelo.Empleado;

/**
 *
 * @author Jainer Pinta
 */
public class EmpleadoController extends PersonaController{
    private Empleado empleado;
    private Lista<Empleado> empleados;
    
    /**
     * Obtiene el empleado del controlador
     * @return Objeto tipo Empleado
     */
    public Empleado getEmpleado() {
        if(empleado == null)
            empleado = new Empleado();
        return empleado;
    }
    
    /**
     * Setear empleado
     * @param empleado Objeto tipo empleado 
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    /**
     * Obtener lista de empleados
     * @return Lista de tipo Empleado
     */
    public Lista<Empleado> getEmpleados() {
        if(this.empleados == null)
            this.empleados = new Lista<>();
        return this.empleados;
    }

    /**
     * Setear lista de empleados
     * @param empleados Lista de tipo Empleado
     */
    public void setEmpleados(Lista<Empleado> empleados) {
        this.empleados = empleados;
        empleados.setClazz(Empleado.class.getSuperclass());
    }

    /**
     * Asigna autorizacion a empleados repecionista y gerente
     * @param cargo Nombre del cargo
     * @return True si el cargo es recepcionista o gerente
     */
    public boolean asignarAutorizacion(String cargo){
        return (cargo.equalsIgnoreCase("recepcionista") || cargo.equalsIgnoreCase("gerente"));
    }
    
    /**
     * Obtener una lista de empleados que contengan los parametros enviados
     * @param dato  String dato a buscar en la lista de empleados
     * @param atributo Atributo del cual se quiere buscar el dato
     * @return Retorna una lisa de tipo empleado que contengan los parametros enviados.
     */
    public Lista<Empleado> buscarEmpleado(String dato, String atributo){
        Lista<Empleado> resultadoBusqueda = new Lista();
        resultadoBusqueda.setClazz(Empleado.class.getSuperclass());
        empleados.quicksort(0, empleados.sizeLista()-1, atributo, Lista.ASCENDENTE);
        if(atributo.equalsIgnoreCase("cedula")){
            Empleado resultado = empleados.busquedaBinaria(dato, atributo); 
            if(resultado != null){
                resultadoBusqueda.insertarNodo(resultado);
            }
        }else if(atributo.equalsIgnoreCase("identificacion")){
            resultadoBusqueda.setClazz(Empleado.class);
            empleados.setClazz(Empleado.class);
            Empleado resultado = empleados.busquedaBinaria(dato, atributo); 
            if(resultado != null){
                resultadoBusqueda.insertarNodo(resultado);
            }
        }else{
            resultadoBusqueda = empleados.busquedaSecuencial(dato, atributo);
        }
        return resultadoBusqueda;
    }
    
    /**
     * Genera un reporte en pdf en un directorio seleccionado
     * @param cargo Tipo de empleados que contendra el reporte
     * @param directorio Directorio donde se guardara el pdf.
     * @return Retorna true si el pdf se ha generado correctamente
     */
    public boolean generarReporte(String cargo, String directorio){
        try{
            Lista <Empleado> empleadosReporte = empleados;
            if(!cargo.equalsIgnoreCase("todo")){
                Lista <Empleado> aux = new Lista<>();
                for(int i = 0; i < empleadosReporte.sizeLista(); i++){
                    if (empleadosReporte.consultarDatoPosicion(i).getRol().getCargo().equalsIgnoreCase(cargo)) {
                        aux.insertarNodo(empleadosReporte.consultarDatoPosicion(i));
                    }
                }
                empleadosReporte = aux;
            }
            PdfWriter writer = new PdfWriter(directorio+"\\Reporte_"+LocalDate.now()+".pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document documento = new Document(pdf);
            documento.add(new Paragraph("SISTEMA HOTELERO ").setTextAlignment(TextAlignment.CENTER));
            documento.add(new Paragraph("REPORTE DE EMPLEADOS").setTextAlignment(TextAlignment.CENTER));
            documento.add(new Paragraph("Fecha de reporte: "+LocalDate.now().toString()).setTextAlignment(TextAlignment.CENTER));

            Table tabla = new Table(new float[]{1,1,1,1,1,1,1,1});
            tabla.setWidth(UnitValue.createPercentValue(100));
            tabla.addHeaderCell("ID");
            tabla.addHeaderCell("Nombres");
            tabla.addHeaderCell("Apellidos");
            tabla.addHeaderCell("Fecha de Nacimiento");
            tabla.addHeaderCell("Telefono");
            tabla.addHeaderCell("Dirección");
            tabla.addHeaderCell("Cedula");
            tabla.addHeaderCell("Cargo");   

            for (int i = 0; i < empleadosReporte.sizeLista(); i++) {
                Empleado aux = empleadosReporte.consultarDatoPosicion(i);
                tabla.addCell(aux.getIdentificacion());
                tabla.addCell(aux.getNombres());
                tabla.addCell(aux.getApellidos());
                tabla.addCell(aux.getFechaNacimiento().toString());
                tabla.addCell(aux.getTelefono());
                tabla.addCell(aux.getDireccion());
                tabla.addCell(aux.getCedula());
                tabla.addCell(aux.getRol().getCargo());
            }
            documento.add(tabla);
            documento.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Verfica si un id de empleado ya esta en uso.
     * @param id El id a verificar.
     * @return Retorna true si el id esta disponible.
     */
    public boolean verificarDisponibiladID(String id){
        Lista <Empleado> aux = buscarEmpleado(id, "identificacion");
        for(int i = 0; i < aux.sizeLista(); i++){
            if(aux.consultarDatoPosicion(i)==null){
                return false;
            }else{
                if(aux.consultarDatoPosicion(i).getIdentificacion().equalsIgnoreCase(id)){
                    return false;
                }
            }
        }
        return true;
    }
}
