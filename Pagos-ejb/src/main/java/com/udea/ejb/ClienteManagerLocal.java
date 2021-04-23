/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.ejb;

import com.udea.persistence.TsCliente;
import javax.ejb.Local;

/**
 *
 * @author Esteban
 */
@Local
public interface ClienteManagerLocal {

    TsCliente getClienteById(String clienteId);

    void createCliente(TsCliente cliente);

}
