/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Fredy
 */
@Entity
@Table(name = "LINEA")
@NamedQueries({
    @NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l"),
    @NamedQuery(name = "Linea.findById", query = "SELECT l FROM Linea l WHERE l.id = :id"),
    @NamedQuery(name = "Linea.findByNombre", query = "SELECT l FROM Linea l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Linea.findByAdmiistrador", query = "SELECT l FROM Linea l WHERE l.idAdmon.id = :idAdmon"),
    @NamedQuery(name = "Linea.findByTasa", query = "SELECT l FROM Linea l WHERE l.tasa = :tasa")})
public class Linea implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(generator="LineaSeq") 
    @SequenceGenerator(name="LineaSeq",sequenceName="SEQ_LINEA", allocationSize=1) 
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TASA")
    private Double tasa;
    @JoinColumn(name = "ID_ADMON", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Administrador idAdmon;
    @OneToMany(mappedBy = "idLinea", fetch = FetchType.LAZY)
    private List<PlanPago> planPagoList;

    public Linea() {
    }

    public Linea(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTasa() {
        return tasa;
    }

    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }

    public Administrador getIdAdmon() {
        return idAdmon;
    }

    public void setIdAdmon(Administrador idAdmon) {
        this.idAdmon = idAdmon;
    }

    public List<PlanPago> getPlanPagoList() {
        return planPagoList;
    }

    public void setPlanPagoList(List<PlanPago> planPagoList) {
        this.planPagoList = planPagoList;
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
        if (!(object instanceof Linea)) {
            return false;
        }
        Linea other = (Linea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.cloud.simuladorcredito.jpa.Linea[ id=" + id + " ]";
    }
    
}
