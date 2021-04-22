/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.ejb;

import com.udea.persistence.TsCliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Esteban
 */
@Stateless
public class ClienteManager implements ClienteManagerLocal {

    @PersistenceContext(unitName = "com.udea_Pagos-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public TsCliente getClienteById(int clienteId) {
        Query query = em.createNamedQuery("TsCliente.findByCtId").setParameter("ctId", clienteId);
        return (TsCliente) query.getSingleResult();
    }

    @Override
    public void createCliente(TsCliente cliente) {
        em.persist(cliente);
    }

    
    
}
