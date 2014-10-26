/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.cloud.simuladorcredito.persistencia;

import co.edu.uniandes.cloud.simuladorcredito.jpa.PlanPago;
import co.edu.uniandes.cloud.simuladorcredito.jpa.SuperPojo;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class PlanPagoDAO extends SuperDAO<PlanPago>{

    @Override
    public SuperPojo insertar(SuperPojo objeto) {
        PlanPago planPago=(PlanPago) objeto;
        planPago.setFechaCreacion(new Date());
        return super.insertar(planPago); //To change body of generated methods, choose Tools | Templates.
    }
    
}
