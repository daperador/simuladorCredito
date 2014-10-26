/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.dao.PlanPagoDAO;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.PlanPago;
import co.edu.uniandes.csw.simuladorcredito.utils.ColaWorkerUtil;
import co.edu.uniandes.csw.simuladorcredito.utils.RegistroException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            PlanPago plan=(PlanPago)new PlanPagoDAO().insertar(planPago);
            ColaWorkerUtil.crearMensaje(plan.getId().toString());
            return plan;
        } catch (Exception ex) {
            Logger.getLogger(PlanPagoServicio.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegistroException("Error al guardar el plan de pagos");
        }
    }
    
}
