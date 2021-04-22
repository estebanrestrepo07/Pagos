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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Esteban
 */
@Entity
@Table(name = "ma_franquicias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaFranquicias.findAll", query = "SELECT m FROM MaFranquicias m")
    , @NamedQuery(name = "MaFranquicias.findByFqId", query = "SELECT m FROM MaFranquicias m WHERE m.fqId = :fqId")
    , @NamedQuery(name = "MaFranquicias.findByFqNombre", query = "SELECT m FROM MaFranquicias m WHERE m.fqNombre = :fqNombre")
    , @NamedQuery(name = "MaFranquicias.findByFqRangoInf", query = "SELECT m FROM MaFranquicias m WHERE m.fqRangoInf = :fqRangoInf")
    , @NamedQuery(name = "MaFranquicias.findByFqRangoSu", query = "SELECT m FROM MaFranquicias m WHERE m.fqRangoSu = :fqRangoSu")})
public class MaFranquicias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fq_id")
    private Integer fqId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "fq_nombre")
    private String fqNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fq_rango_inf")
    private long fqRangoInf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fq_rango_su")
    private long fqRangoSu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maFranquiciasFqId")
    private Collection<TsTransaccion> tsTransaccionCollection;

    public MaFranquicias() {
    }

    public MaFranquicias(Integer fqId) {
        this.fqId = fqId;
    }

    public MaFranquicias(Integer fqId, String fqNombre, long fqRangoInf, long fqRangoSu) {
        this.fqId = fqId;
        this.fqNombre = fqNombre;
        this.fqRangoInf = fqRangoInf;
        this.fqRangoSu = fqRangoSu;
    }

    public Integer getFqId() {
        return fqId;
    }

    public void setFqId(Integer fqId) {
        this.fqId = fqId;
    }

    public String getFqNombre() {
        return fqNombre;
    }

    public void setFqNombre(String fqNombre) {
        this.fqNombre = fqNombre;
    }

    public long getFqRangoInf() {
        return fqRangoInf;
    }

    public void setFqRangoInf(long fqRangoInf) {
        this.fqRangoInf = fqRangoInf;
    }

    public long getFqRangoSu() {
        return fqRangoSu;
    }

    public void setFqRangoSu(long fqRangoSu) {
        this.fqRangoSu = fqRangoSu;
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
        hash += (fqId != null ? fqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaFranquicias)) {
            return false;
        }
        MaFranquicias other = (MaFranquicias) object;
        if ((this.fqId == null && other.fqId != null) || (this.fqId != null && !this.fqId.equals(other.fqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udea.persistence.MaFranquicias[ fqId=" + fqId + " ]";
    }
    
}
