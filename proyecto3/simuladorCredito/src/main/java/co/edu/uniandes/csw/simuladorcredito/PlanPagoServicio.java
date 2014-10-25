/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.dao.PlanPagoDAO;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.PlanPago;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author Daniel
 */
@Path("planPago")
public class PlanPagoServicio {
    
    @POST
    public PlanPago insertarPlanPagos(PlanPago planPago){
        return (PlanPago)new PlanPagoDAO().insertar(planPago);
    }
    
}
