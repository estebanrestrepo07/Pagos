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
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Esteban
 */
public class PagosMBean implements Serializable {
    private String nombreCliente;
    private String cedulaCliente;
    private String email;
    private long numTarjeta;
    private short cvv;
    private String tipoTarjeta;
    private double cantidad;
    
    private boolean clienteError = false;
    private boolean transaccionError = false;
    
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

    public TsCliente getCliente() {
        return clienteManager.getClienteById(cliente.getCtId());
    }
    
    public String getTransaction() {
        return transaccion.getTsNombreTitular();
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

    public long getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(long numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public short getCvv() {
        return cvv;
    }

    public void setCvv(short cvv) {
        this.cvv = cvv;
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
    
    
    
    /////////////////////////

    //Action handler - llamado cuando guarda un pago
    public String guardarPago() {
        
        try{
            //ver si el usuario ya existe, si no existe, crear uno nuevo, si ya existe, hacer las transaccion
            
            TsCliente existCliente = this.clienteManager.getClienteById(cedulaCliente);
            
            //aqui como el usuario no existe, envia una excepcion, verificar que si entre al catch o revisar el catch desde el cliente manager!!!! 
            
        }catch(Exception eC){
            clienteError = true;
           
            
            //aaqui se llena los datos del cliente para posterior crearlo en caso de no existir
            
//            this.clienteManager.getClienteById(cedulaCliente);
//            TsCliente actualCliente = new TsCliente();
//            actualCliente.setCtId(cedulaCliente);
//            actualCliente.setCtNombre(nombreCliente);
//            actualCliente.setCtEmail(email);
//            actualCliente.setCtCelular("123");
//            this.clienteManager.createCliente(actualCliente);
            
        } finally {
//            if(clienteError == false){
//                try{
//                    aqui se llena los datos de la transaccion para posterior crearla en caso de haber creado o haber obtenido el cliente! 

//                    TsTransaccion actualTransaccion = new TsTransaccion();
//                    actualTransaccion.setTsNumTarjeta(numTarjeta);
//                    actualTransaccion.setTsNombreTitular(nombreCliente);//o nombre Titular
//                    actualTransaccion.setFranquicia(1);
//                    actualTransaccion.setTsCliente(cliente);
//                    actualTransaccion.setTsMonto(cantidad);
////                    this.transaccionManager.createTransaccion(actualTransaccion);
//                }catch(Exception eT){
//                    transaccionError = true;
//                }
//                finally{
//////                    if(transaccionError == false){
//////                         //remove cliente creado
//////                    }
////                
//                }
//            }
        } 
        return "CREATED";
    }
//    
    //Action handler - llamado cuando guarda un pago
    public String volverPagInicial() {
        return "BACK";
    }
}
