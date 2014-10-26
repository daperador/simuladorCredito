/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.negocio;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Cuota;
import co.edu.uniandes.cloud.simuladorcredito.jpa.PlanPago;
import co.edu.uniandes.cloud.simuladorcredito.persistencia.AdministradorPersistencia;
import co.edu.uniandes.cloud.simuladorcredito.util.Constantes;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import javax.ejb.EJB;
//import javax.ejb.Schedule;
//import javax.ejb.Stateless;

/**
 *
 * @author Fredy
 */
//@Stateless
public class Process {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    //@EJB
    private AdministradorPersistencia dao=new AdministradorPersistencia();
    
    //@Schedule(minute = "*/3", hour="*", dayOfMonth = "*")
    public void procesar(){
        System.out.println("Procesando..."+Calendar.getInstance());
        
        List<PlanPago> pps=dao.consultarPlanesEstado(Constantes.ESTADO_EN_PROCESO);
        AmortizacionFrances aa = new AmortizacionFrances();
        for (PlanPago pp:pps){
            List<Cuota> cuotas=aa.generarCuotas(pp.getValor(), pp.getIdLinea().getTasa(), pp.getPlazo(), pp.getId());
            for (Cuota c:cuotas){
                System.out.println(c);
                dao.guardarCuota(c);
            }
            pp.setEstado(Constantes.ESTADO_GENERADO);
            pp.setNivelRiesgo(this.calcularNivelRiesgo());
            pp.setFechaModificacion(new Date());
            dao.actualizarPlan(pp);
        }
    }
    
    public double calcularNivelRiesgo(){
        Calendar inicio=Calendar.getInstance();
        double diferencia=0;
        do{
            Calendar ahora=Calendar.getInstance();
            diferencia = (int)((ahora.getTimeInMillis() - inicio.getTimeInMillis())/1000);
            //System.out.println(diferencia);
        }while(diferencia<25);
        
        return 1+(int)(Math.random()*10);
    }
    
    public static void main(String args[]){
        new Process().procesar();
    }
}
