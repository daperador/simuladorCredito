/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Fredy
 */
@Entity
@Table(name = "PLAN_PAGO")
@NamedQueries({
    @NamedQuery(name = "PlanPago.findAll", query = "SELECT p FROM PlanPago p"),
    @NamedQuery(name = "PlanPago.findById", query = "SELECT p FROM PlanPago p WHERE p.id = :id"),
    @NamedQuery(name = "PlanPago.findByFechaCreacion", query = "SELECT p FROM PlanPago p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PlanPago.findByDocumento", query = "SELECT p FROM PlanPago p WHERE p.documento = :documento"),
    @NamedQuery(name = "PlanPago.findByValor", query = "SELECT p FROM PlanPago p WHERE p.valor = :valor"),
    @NamedQuery(name = "PlanPago.findByPlazo", query = "SELECT p FROM PlanPago p WHERE p.plazo = :plazo"),
    @NamedQuery(name = "PlanPago.findByEstado", query = "SELECT p FROM PlanPago p WHERE p.estado = :estado"),
    @NamedQuery(name = "PlanPago.findByNivelRiesgo", query = "SELECT p FROM PlanPago p WHERE p.nivelRiesgo = :nivelRiesgo"),
    @NamedQuery(name = "PlanPago.findByFechaNacimiento", query = "SELECT p FROM PlanPago p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "PlanPago.findByFechaModificacion", query = "SELECT p FROM PlanPago p WHERE p.fechaModificacion = :fechaModificacion")})
public class PlanPago implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(generator="PlanSeq") 
    @SequenceGenerator(name="PlanSeq",sequenceName="SEQ_PLAN", allocationSize=1) 
    private BigDecimal id;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 20)
    @Column(name = "DOCUMENTO")
    private String documento;
    @Column(name = "VALOR")
    private BigInteger valor;
    @Column(name = "PLAZO")
    private BigInteger plazo;
    @Size(max = 20)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "NIVEL_RIESGO")
    private Double nivelRiesgo;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "ID_LINEA", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Linea idLinea;

    public PlanPago() {
    }

    public PlanPago(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public BigInteger getPlazo() {
        return plazo;
    }

    public void setPlazo(BigInteger plazo) {
        this.plazo = plazo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(Double nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Linea getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Linea idLinea) {
        this.idLinea = idLinea;
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
        if (!(object instanceof PlanPago)) {
            return false;
        }
        PlanPago other = (PlanPago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.cloud.simuladorcredito.jpa.PlanPago[ id=" + id + " ]";
    }
    
}
