/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.ejb;

import com.udea.persistence.TsTransaccion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Esteban
 */
@Stateless
public class TransaccionManager implements TransaccionManagerLocal {

    @PersistenceContext(unitName = "com.udea_Pagos-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    public TsTransaccion getTransaccion(int tsTransaccionId) {
        Query query = em.createNamedQuery("TsTransaccion.findAll").setParameter("tsId", tsTransaccionId);
        return (TsTransaccion) query.getSingleResult();
    }

    @Override
    public void createTransaccion(TsTransaccion transaccion) {
        em.persist(transaccion);
    }
    
    
    
}
