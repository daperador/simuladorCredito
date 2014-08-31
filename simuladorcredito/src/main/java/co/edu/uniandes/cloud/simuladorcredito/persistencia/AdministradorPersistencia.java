/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.persistencia;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Administrador;
import co.edu.uniandes.cloud.simuladorcredito.jpa.Cuota;
import co.edu.uniandes.cloud.simuladorcredito.jpa.Linea;
import co.edu.uniandes.cloud.simuladorcredito.jpa.PlanPago;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Stateless
public class AdministradorPersistencia implements Serializable {
    
    @PersistenceContext(unitName = "simuladorCreditoPU")
    private EntityManager em;
    
    public void crearAdministrador(Administrador administrador){
        em.persist(administrador);
        administrador.setUrl("/planPagos.xhtml?id="+administrador.getId().longValue());
    }
    
    public Administrador consultarAdministradorCorreo(String email){
        List<Administrador> lista=em.createNamedQuery("Administrador.findByEmail").setParameter("email", email).getResultList();
        return lista.isEmpty()?null:lista.get(0);
    }
    
    public List<Linea> consultarLineasAdministrador(Integer idAdministrador){
        return em.createNamedQuery("Linea.findByAdmiistrador").setParameter("idAdmon", idAdministrador).getResultList();
    }
    
    public PlanPago guardarPlanPagos(PlanPago  pp){
        em.persist(pp);
        return pp;
    }
    
    public List<Cuota> consultarCuotas(PlanPago pp){
        return em.createNamedQuery("Cuota.findByIdPlan").setParameter("idPlan", pp).getResultList();
    }
    
    public List<PlanPago> consultarPlanesEstado(String estado){
        return em.createNamedQuery("PlanPago.findByEstado").setParameter("estado", estado).getResultList();
    }
    
    public void guardarCuota(Cuota c){
        em.persist(c);
    }
    
    public PlanPago actualizarPlan(PlanPago pp){
        return em.merge(pp);
    }
    
    public PlanPago consultarPlanPago(Integer id){
        return (PlanPago)em.createNamedQuery("PlanPago.findById").setParameter("id", id).getSingleResult();
    }
    
    public boolean login(String email, String password) {
        List<Administrador> lista=em.createNamedQuery("Administrador.findByEmailContrasena").setParameter("email", email).setParameter("contrasena", password).getResultList();
        return !lista.isEmpty();
    }
}
