/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Fredy
 */
@Entity
@Table(name = "CUOTA")
@NamedQueries({
    @NamedQuery(name = "Cuota.findAll", query = "SELECT c FROM Cuota c"),
    @NamedQuery(name = "Cuota.findById", query = "SELECT c FROM Cuota c WHERE c.id = :id"),
    @NamedQuery(name = "Cuota.findByIdPlan", query = "SELECT c FROM Cuota c WHERE c.idPlan = :idPlan"),
    @NamedQuery(name = "Cuota.findByNumeroCuota", query = "SELECT c FROM Cuota c WHERE c.numeroCuota = :numeroCuota"),
    @NamedQuery(name = "Cuota.findByIntereses", query = "SELECT c FROM Cuota c WHERE c.intereses = :intereses"),
    @NamedQuery(name = "Cuota.findByCapital", query = "SELECT c FROM Cuota c WHERE c.capital = :capital"),
    @NamedQuery(name = "Cuota.findByTotal", query = "SELECT c FROM Cuota c WHERE c.total = :total"),
    @NamedQuery(name = "Cuota.findBySaldo", query = "SELECT c FROM Cuota c WHERE c.saldo = :saldo")})
public class Cuota implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ID_PLAN")
    private BigInteger idPlan;
    @Column(name = "NUMERO_CUOTA")
    private BigInteger numeroCuota;
    @Column(name = "INTERESES")
    private Double intereses;
    @Column(name = "CAPITAL")
    private Double capital;
    @Column(name = "TOTAL")
    private Double total;
    @Column(name = "SALDO")
    private Double saldo;

    public Cuota() {
    }

    public Cuota(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(BigInteger idPlan) {
        this.idPlan = idPlan;
    }

    public BigInteger getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(BigInteger numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public Double getIntereses() {
        return intereses;
    }

    public void setIntereses(Double intereses) {
        this.intereses = intereses;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuota)) {
            return false;
        }
        Cuota other = (Cuota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.cloud.simuladorcredito.jpa.Cuota[ id=" + id + " ]";
    }
    
}
