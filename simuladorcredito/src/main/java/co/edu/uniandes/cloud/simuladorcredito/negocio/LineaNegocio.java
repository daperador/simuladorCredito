/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.negocio;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Linea;
import co.edu.uniandes.cloud.simuladorcredito.persistencia.LineaPersistencia;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Daniel
 */
@Stateless
public class LineaNegocio {
    
    @EJB
    private LineaPersistencia lineaPersistencia;
    
    public Linea getLinea(Integer id){
        return lineaPersistencia.getLinea(id);
    }
    
    /*public List<Linea> getLineas(){
        return lineaPersistencia.getLineas();
    }*/
    
    public Linea actualizarLinea(Linea linea){
        return null;
    }
    
    public void borrarLinea(BigDecimal id){
        return ;
    }
    
}
