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
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Esteban
 */
public class PagosMBean implements Serializable {

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
    
    
    public TsCliente getCliente(){
        return clienteManager.getClienteById(cliente.getCtId());
    }
    
    //Action handler - llamado cuando guarda un pago
    public String guardarPago(TsCliente cliente, TsTransaccion transaccion){
        //hacer try catch o promesa para controlar;
//        clienteManager.createCliente(cliente);
//        transaccionManager.createTransaccion(transaccion);
        
        return "CREATED";
    }
    
    
    //Action handler - llamado cuando guarda un pago
    public String volverPagInciial(){        
        return "BACK";
    }
    
}
