/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
    @NamedQuery(name = "Cuota.findByIdPlan", query = "SELECT c FROM Cuota c WHERE c.idPlan = :idPlan ORDER BY c.numeroCuota"),
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
    @GeneratedValue(generator="CuotaSeq") 
    @SequenceGenerator(name="CuotaSeq",sequenceName="SEQ_CUOTA", allocationSize=1) 
    private BigDecimal id;
    @JoinColumn(name = "ID_PLAN")
    @ManyToOne(cascade = {},fetch = FetchType.EAGER)
    private PlanPago idPlan;
    @Column(name = "NUMERO_CUOTA")
    private Integer numeroCuota;
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

    public PlanPago getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(PlanPago idPlan) {
        this.idPlan = idPlan;
    }

    public Integer getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(Integer numeroCuota) {
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
        return this.numeroCuota+"\t"+this.total+"\t"+this.capital+"\t"+this.intereses+"\t"+this.saldo;
    }
    
}
