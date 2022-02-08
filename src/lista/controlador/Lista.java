/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.controlador;

import java.io.Serializable;
import java.lang.reflect.Field;
import lista.modelo.Nodo;

/**
 *
 * @author Jainer Pinta
 */
public class Lista <T> implements Serializable{
    public static final Integer ASCENDENTE = 1;
    public static final Integer DESCENDENTE = 2;
    private Nodo cabecera;
    private Class clazz;
    
    public boolean estaVacias(){
        return this.getCabecera() == null;
    }
    /**
     * Permite insertar dato en la lista
     * @param dato El dato a ingresar
     */
    private void insertar(T dato){
        Nodo nodo = new Nodo(dato, getCabecera());
        setCabecera(nodo);
    }
    
    private void insertarFinal(T dato){
        insertar(dato, sizeLista());
    }
    /**
     * Insertar un dato por posicion
     * @param dato El dato a insertar
     * @param pos La posicion a insertar
     */
    public void insertar(T dato, int pos){
        if (estaVacias() || pos<=0) {
            insertar(dato);
        }else{
            Nodo iterador = getCabecera();
            for(int i=0; i<pos-1; i++){
                if (iterador.getNodoSiguiente() == null) {
                    break;
                }
                iterador = iterador.getNodoSiguiente();
            }
            Nodo tmp = new Nodo(dato, iterador.getNodoSiguiente());
            iterador.setNodoSiguiente(tmp);
        }
    }
    
    /**
     * Agregar item a lista descendente
     * @param dato El dato a agregar
     */
    public void insertarNodo(T dato){
        if (sizeLista()>0) {
            insertarFinal(dato);
        }else{
            insertar(dato);
        }
    }
    /**
     * Retorn el tamanio de la lista
     * @return numero de eelementos
     */
    public int sizeLista(){
        Nodo tmp = getCabecera();
        int contador = 0;
        while (!estaVacias() && tmp != null) {            
            contador++;
            tmp  = tmp.getNodoSiguiente();
        }
        return contador;
    }
    /**
     * Permite extraer el primer dato de la lista
     * @return El dato
     */
    public T extraer(){
        T dato = null;
        if (!estaVacias()) {
            dato = (T)getCabecera().getDato();
            setCabecera(getCabecera().getNodoSiguiente());
        }
        return dato;
    }
    /**
     * Permite consultar un dato de la lista por posicion
     * @param posicion posicion en la lista
     * @return dato encontrado en la posicion
     */
    public T consultarDatoPosicion(int posicion){
        T dato = null;
        if (!estaVacias() && (posicion >= 0 && posicion <= sizeLista()-1)) {       
            Nodo tmp = getCabecera();        
            for (int i = 0; i < posicion; i++) {
                tmp = tmp.getNodoSiguiente();
                if (tmp == null)break;
            }
            if (tmp != null) {
                dato = (T)tmp.getDato();
            }
        }
        return dato;
    }
    
    public void imprimir(){
        Nodo tmp = getCabecera();
        while (!estaVacias() && tmp != null) {            
            System.out.println(tmp.getDato());
            tmp  = tmp.getNodoSiguiente();
        }
    }
    
    public Lista<T> busquedaSecuencial(String dato, String atributo){
        Lista<T> listaResultados = new Lista();
        try{
            for (int i = 0; i < sizeLista(); i++) {
                Object datoAux = value(consultarDatoPosicion(i), atributo);
                if (datoAux instanceof Number) {
                    Number datoC = (Number)datoAux;
                    Double datoBuscar = Double.valueOf(dato);
                    if (datoBuscar==datoC.doubleValue()) {
                        listaResultados.insertarNodo(consultarDatoPosicion(i));
                    }
                }else{
                    if (datoAux.toString().contains(dato)) {
                        listaResultados.insertarNodo(consultarDatoPosicion(i));
                    }
                }        
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaResultados;
    }
    
    public T busquedaBinaria(String dato, String atributo){
        try{
            int posCentral, posInicial, posFinal;
            posInicial = 0;
            posFinal = sizeLista()-1;
            T datoCentral;
            while(posInicial <= posFinal){
                posCentral = (posInicial+posFinal)/2;
                datoCentral = consultarDatoPosicion(posCentral);
                boolean band = false;
                Object datoAux = value(datoCentral, atributo);
                if (datoAux instanceof Number) {
                    Number datoC = (Number)datoAux;
                    Double datoBuscar = Double.valueOf(dato);
                    if (datoBuscar==datoC.doubleValue()) {
                        return (T)datoCentral;
                    }
                    band = (datoC.doubleValue() > datoBuscar);
                }else{
                    if (datoAux.toString().equals(dato)) {
                        return datoCentral;
                    }
                    band = (datoAux.toString().compareTo(dato)>0);
                }
                if (band) {
                    posFinal = posCentral-1;
                }else{
                    posInicial = posCentral+1;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;   
    }
    
    public void borrar(int posicion){
        if (!estaVacias() && (posicion >= 0 && posicion <= sizeLista()-1)) {
            if (posicion == 0) {
                setCabecera(getCabecera().getNodoSiguiente());
            }else{
                Nodo aux = getCabecera();
                Nodo nodoAux = null;
                for (int i = 0; i < sizeLista(); i++) {
                    if (posicion!=i) {
                        Nodo nodo = new Nodo(aux.getDato(), nodoAux);
                        nodoAux = nodo;
                    }
                    aux = aux.getNodoSiguiente();
                    if (aux == null)break;
                }
                this.setCabecera(nodoAux);
            }
        }
    }
    
    
    public boolean modificarPorPos(T dato, int pos){
        if (!estaVacias() && (pos<=sizeLista()-1) && pos>=0) {
            Nodo iterador = getCabecera();
            for(int i=0; i<pos; i++){
                iterador = iterador.getNodoSiguiente();
                if (iterador == null) {
                    break;
                }
            }
            if (iterador!=null) {
                iterador.setDato(dato);
                return true;
            }    
        }
        return false;
    }
    
    private Field getField(String nombre) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(nombre)) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }
    
//    public void testReflect(T dato, String atributo){
//        try{
//            System.out.println(getField(atributo).get(dato).toString());                      
//        }catch(Exception e){
//            System.out.println("error" +e);
//        }
//    }
    
    public Object value(T dato, String atributo) throws Exception {
        return getField(atributo).get(dato);
    }
    
    public Lista<T> quicksort(int izq, int der, String atributo, Integer ordenacion){
        try{
            int i, ult, m;
            if (izq >= der)
                return this;
            T tmp = consultarDatoPosicion(izq);
            m = (izq+der)/2;
            modificarPorPos(consultarDatoPosicion(m), izq);
            modificarPorPos(tmp, m);
            ult = izq;
            for (i = izq+1; i <= der; i++){
                boolean band = false;
                Object datoI = value(consultarDatoPosicion(i), atributo);
                Object datoIzq  = value(consultarDatoPosicion(izq), atributo);
                if (datoI instanceof Number) {
                    Number aux1 = (Number)datoI;
                    Number aux2 = (Number)datoIzq;
                    band = (ordenacion.intValue() == Lista.ASCENDENTE.intValue())
                        ? aux1.doubleValue() < aux2.doubleValue()
                        : aux1.doubleValue() > aux2.doubleValue(); 
                }else{
                    band = (ordenacion.intValue() == Lista.ASCENDENTE.intValue())
                            ? datoI.toString().compareTo(datoIzq.toString())<0
                            : datoI.toString().compareTo(datoIzq.toString())>0;
                }
                if (band) {
                    tmp = consultarDatoPosicion(++ult);
                    modificarPorPos(consultarDatoPosicion(i), ult);
                    modificarPorPos(tmp, i);
                }
            }
            tmp = consultarDatoPosicion(izq);
            modificarPorPos(consultarDatoPosicion(ult), izq);
            modificarPorPos(tmp, ult);                
            quicksort(izq, ult-1, atributo, ordenacion);
            quicksort(ult+1, der, atributo, ordenacion);
        }catch(Exception e){
            e.printStackTrace();
        }
        return this;
    }
    
    public Lista<T> shell (String atributo, Integer ordenacion){
        try{
            int intervalo, i, j, k;
            intervalo = sizeLista()/2;
            while(intervalo>0){
                for (i = intervalo; i < sizeLista(); i++) {
                    j = i-intervalo;
                    while(j>=0){
                        k = j+intervalo;
                        boolean band = false;
                        Object datoI = value(consultarDatoPosicion(j), atributo);
                        Object datoIzq  = value(consultarDatoPosicion(k), atributo);
                        if (datoI instanceof Number) {
                            Number aux1 = (Number)datoI;
                            Number aux2 = (Number)datoIzq;
                            band = (ordenacion.intValue() == Lista.ASCENDENTE.intValue())
                                ? aux1.doubleValue() < aux2.doubleValue()
                                : aux1.doubleValue() > aux2.doubleValue(); 
                        }else{
                            band = (ordenacion.intValue() == Lista.ASCENDENTE.intValue())
                                    ? datoI.toString().compareTo(datoIzq.toString())<0
                                    : datoI.toString().compareTo(datoIzq.toString())>0;
                        }
                        if(band){
                            j = -1;
                        }else{
                            T aux = consultarDatoPosicion(j);
                            modificarPorPos(consultarDatoPosicion(k), j);
                            modificarPorPos(aux, k);
                            j -= intervalo;
                        }
                    }                  
                }
                intervalo = intervalo/2;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return this;
    }
    
    public Lista<T> seleccion_clase(String atributo, Integer ordenacion){
        //Lista<T> a = this;
        try{
            int i,j,k = 0;
            T t = null;
            int n = sizeLista();
            for (i = 0; i < n-1; i++) {
                k = i;
                t = consultarDatoPosicion(i);
                for (j = i+1; j < n; j++) {
                    boolean band = false;
                    Object datoT = value(t, atributo);
                    Object datoJ = value(consultarDatoPosicion(j), atributo);
                    if (datoT instanceof Number) {
                        Number aux = (Number)datoT;
                        Number numero = (Number)datoJ;
                        band = (ordenacion.intValue() == Lista.ASCENDENTE.intValue())
                            ? numero.doubleValue() < aux.doubleValue()
                            : numero.doubleValue() > aux.doubleValue();               
                    }else{
                        band = (ordenacion.intValue() == Lista.ASCENDENTE.intValue())
                                ? datoT.toString().compareTo(datoJ.toString())>0
                                : datoT.toString().compareTo(datoJ.toString())<0;                
                    }
                    if (band) {
                        t = consultarDatoPosicion(j);
                        k = j;
                    }
                }
                modificarPorPos(consultarDatoPosicion(i), k);
                modificarPorPos(t, i);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return this;
    }
    
    public Nodo getCabecera() {
        return cabecera;
    }

    public void setCabecera(Nodo cabecera) {
        this.cabecera = cabecera;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
