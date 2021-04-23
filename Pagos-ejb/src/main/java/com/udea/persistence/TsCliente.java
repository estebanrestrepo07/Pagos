/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.persistence;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "ts_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TsCliente.findAll", query = "SELECT t FROM TsCliente t")
    , @NamedQuery(name = "TsCliente.findByCtId", query = "SELECT t FROM TsCliente t WHERE t.ctId = :ctId")
    , @NamedQuery(name = "TsCliente.findByCtNombre", query = "SELECT t FROM TsCliente t WHERE t.ctNombre = :ctNombre")
    , @NamedQuery(name = "TsCliente.findByCtEmail", query = "SELECT t FROM TsCliente t WHERE t.ctEmail = :ctEmail")
    , @NamedQuery(name = "TsCliente.findByCtCelular", query = "SELECT t FROM TsCliente t WHERE t.ctCelular = :ctCelular")})
public class TsCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Size(max = 11)
    @Column(name = "ct_id")
    private String ctId;
    @Size(max = 45)
    @Column(name = "ct_nombre")
    private String ctNombre;
    @Size(max = 45)
    @Column(name = "ct_email")
    private String ctEmail;
    @Size(max = 45)
    @Column(name = "ct_celular")
    private String ctCelular;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tsCliente")
    private Collection<TsTransaccion> tsTransaccionCollection;

    public TsCliente() {
    }

    public TsCliente(String ctId) {
        this.ctId = ctId;
    }

    public String getCtId() {
        return ctId;
    }

    public void setCtId(String ctId) {
        this.ctId = ctId;
    }

    public String getCtNombre() {
        return ctNombre;
    }

    public void setCtNombre(String ctNombre) {
        this.ctNombre = ctNombre;
    }

    public String getCtEmail() {
        return ctEmail;
    }

    public void setCtEmail(String ctEmail) {
        this.ctEmail = ctEmail;
    }

    public String getCtCelular() {
        return ctCelular;
    }

    public void setCtCelular(String ctCelular) {
        this.ctCelular = ctCelular;
    }

    @XmlTransient
    public Collection<TsTransaccion> getTsTransaccionCollection() {
        return tsTransaccionCollection;
    }

    public void setTsTransaccionCollection(Collection<TsTransaccion> tsTransaccionCollection) {
        this.tsTransaccionCollection = tsTransaccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctId != null ? ctId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TsCliente)) {
            return false;
        }
        TsCliente other = (TsCliente) object;
        if ((this.ctId == null && other.ctId != null) || (this.ctId != null && !this.ctId.equals(other.ctId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udea.persistence.TsCliente[ ctId=" + ctId + " ]";
    }
    
}
