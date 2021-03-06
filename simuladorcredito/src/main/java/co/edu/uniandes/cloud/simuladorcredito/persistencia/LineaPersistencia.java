/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.persistencia;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Administrador;
import co.edu.uniandes.cloud.simuladorcredito.jpa.Linea;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel
 */
@Stateless
public class LineaPersistencia {
    @PersistenceContext(unitName = "simuladorCreditoPU")
    private EntityManager em;
    
    public Linea getLinea(Integer id){
        Linea resultado=em.find(Linea.class, id);
        em.detach(resultado);
        return resultado;
    }
    
    public List<Linea> getLineas(Administrador administrador){
        return em.createNamedQuery("Linea.findByAdmiistrador").setParameter("idAdmon", administrador.getId()).getResultList();
    }
    
    public Linea actualizarLinea(Linea linea){
        Linea resultado=linea;
        if(linea.getId()==null){
            em.persist(linea);
        }else{
            resultado=em.merge(linea);
        }
        return resultado;
    }
    
    public void borrarLinea(Integer id){
        em.remove(em.find(Linea.class,id));
    }
    
}
