/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.controller;

import com.udea.ejb.ClienteManagerLocal;
import com.udea.ejb.FranquiciasManagerLocal;
import com.udea.persistence.MaFranquicias;
import com.udea.persistence.TsCliente;
import com.udea.persistence.TsTransaccion;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Esteban
 */
public class PagosMBean implements Serializable {

    private String nombreCliente;
    private String cedulaCliente;
    private String email;
    private String numTarjeta;
    private String fechaVen;
    private String cvv;
    private String tipoTarjeta;
    private double cantidad = 500;

    private boolean clienteError = false;
    private boolean transaccionError = false;
    private boolean tipoValido = false;
    private boolean cvvError = false;
    private boolean fechaError = false;

    @EJB
    private FranquiciasManagerLocal franquiciasManager;

    @EJB
    private ClienteManagerLocal clienteManager;

    @EJB
    private com.udea.ejb.TransaccionManagerLocal transaccionManager;

    //propiedades del modelo
    private TsCliente cliente; // crear o traer info del cliente;
    private TsTransaccion transaccion; //crear o traer info de la transaccion;
    private List<MaFranquicias> franquicias; //listado de franquicias para validaciones;

    /**
     * Creates a new instance of PagosMBean
     */
    public PagosMBean() {
    }

    //retorna una lista de franquicias para mostrar en un validaciones de JSF
    public List<MaFranquicias> getFranquicias(){
        if((franquicias==null)||(franquicias.isEmpty()))
            refresh();
            return franquicias;
    }
    
    public TsCliente getCliente() {
        return clienteManager.getClienteById(cliente.getCtId());
    }

    public TsTransaccion getTransaccion() {
        return transaccion;
    }

    public MaFranquicias getFranquicia() {
        return franquiciasManager.getFranquicias().get(0);
    }
    //////////////////////////

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFechaVen() {
        return fechaVen;
    }

    public void setFechaVen(String fechaVen) {
        this.fechaVen = fechaVen;
    }

    
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    private void refresh() {
        franquicias = franquiciasManager.getFranquicias();
    }

/////////////////////////
    //Action handler - llamado cuando guarda un pago
    public String guardarPago() {
        if(!tipoValido){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "tarjeta no valida"));
            tipoTarjeta = "";
            return null;
        }
        
        if(cvvError){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "CVV no valido"));
            cvv = "";
            return null;
        }
        
        if(fechaError){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fecha Vencimiento no valida"));
            fechaVen = "";
            return null;
        }
        
        List<MaFranquicias> franquicias = null;
        clienteError = false;
        try {
            franquicias = franquiciasManager.getFranquicias();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            TsCliente actualCliente = new TsCliente();
            actualCliente.setCtId(cedulaCliente);
            actualCliente.setCtNombre(nombreCliente);
            actualCliente.setCtEmail(email);
            cliente = this.clienteManager.crearCliente(actualCliente);
        } catch (Exception eC) {
            clienteError = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Falló al crear el cliente."));
        } finally {
            if (clienteError == false) {
                try {
                    TsTransaccion actualTransaccion = new TsTransaccion();
                    actualTransaccion.setTsNumTarjeta(Long.parseLong(numTarjeta.replace("-", "")));
                    actualTransaccion.setTsNombreTitular(nombreCliente);//o nombre Titular
                    actualTransaccion.setMaFranquiciasFqId(franquicias.get(0));
                    actualTransaccion.setTsClienteCtId(cliente);
                    actualTransaccion.setTsMonto(cantidad);
                    this.transaccionManager.createTransaccion(actualTransaccion);
                    transaccion = this.transaccionManager.updateTransaccion(actualTransaccion);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Listo!", "Transacción exitosa."));
                    return "CREATED";
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Falló la transacción."));
                }
            }
        }
        return null;
    }
//    
    //Action handler - llamado cuando guarda un pago

    public String volverPagInicial() {
        nombreCliente = "";
        cedulaCliente = "";
        email = "";
        numTarjeta = "";
        cvv = "";
        tipoTarjeta = "";
        cantidad = 500;
        return "BACK";
    }

    //Validador de CVV
    public void validarCVV() {
        cvvError = false;
        System.out.print(cvv);
        if(cvv.length() < 3){
          cvvError = true;
        }
    }
    
    //validador de fecha
    public void validarFecha(){
        fechaError = false;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
        String[] fechaActual = formatter.format(calendar.getTime()).toString().split("-");
        String mesFechaActual = fechaActual[0];
        String anioFechaActual = fechaActual[1];
        if(fechaVen !=null ){
            String[] fechaVenForm = fechaVen.replace("_", "").split("/");
            
            if(fechaVenForm.length==2){
               String mesFechaVen = fechaVenForm[0];
               String anioFechaVen = fechaVenForm[1];
               
               
               if(Integer.parseInt(anioFechaActual) > Integer.parseInt(anioFechaVen)){
                   fechaError = true;
               }else{
                    if(Integer.parseInt(mesFechaActual) > Integer.parseInt(mesFechaVen) || (Integer.parseInt(mesFechaVen) > 13 || Integer.parseInt(mesFechaVen) <= 0)){
                        fechaError = true;
                    
                    }
                   
               }
            }else{
                fechaError = true;
            }
            
        }   
        
        
        
       
          

    }

    //Validador de numTarjeta
    public void validarNumTarjeta() {
        tipoValido = false;
        Iterator<MaFranquicias> iterator = getFranquicias().iterator();
        while (iterator.hasNext() && iterator != null) {
            MaFranquicias franquicia = iterator.next();
            long rangoSup = franquicia.getFqRangoSu();
            long rangoInf = franquicia.getFqRangoInf();
            String numTarjetaString = numTarjeta.replace("-", "").replace("_", "");
            if(numTarjetaString.length()<16){
                tipoTarjeta = "";
                continue;
            }
            long tarjetaActual = Long.parseLong(numTarjetaString.isEmpty() ? "0": numTarjetaString);
            if(rangoInf <= tarjetaActual &&  tarjetaActual <= rangoSup){
                tipoValido = true;
                tipoTarjeta = franquicia.getFqNombre();
                break;
            }
        }
        
        if(tipoValido==false){
            tipoTarjeta = "";
        }
    }
    

}
