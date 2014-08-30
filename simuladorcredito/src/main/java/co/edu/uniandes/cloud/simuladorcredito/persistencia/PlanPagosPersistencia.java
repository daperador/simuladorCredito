/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.cloud.simuladorcredito.persistencia;

import co.edu.uniandes.cloud.simuladorcredito.jpa.PlanPago;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Stateless
public class PlanPagosPersistencia {

    @PersistenceContext(unitName = "simuladorCreditoPU")
    private EntityManager em;

    public List<PlanPago> getPlanesPago(String usuario) {
        return em.createNamedQuery("PlanPago.findByEmailAdministrador")
                .setParameter("email", usuario)
                .getResultList();
    }

}
