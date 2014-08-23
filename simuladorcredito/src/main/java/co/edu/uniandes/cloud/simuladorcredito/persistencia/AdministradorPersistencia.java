/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.persistencia;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Administrador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Stateless
public class AdministradorPersistencia {
    
    @PersistenceContext(unitName = "simuladorCreditoPU")
    private EntityManager em;
    
    public void crearAdministrador(Administrador administrador){
        em.persist(administrador);
        administrador.setUrl("/pyme/"+administrador.getId().longValue());
    }
    
    public Administrador consultarAdministradorCorreo(String email){
        List<Administrador> lista=em.createNamedQuery("Administrador.findByEmail").setParameter("email", email).getResultList();
        return lista.isEmpty()?null:lista.get(0);
    }
}
