/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito.negocio;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Cuota;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fredy
 */
public class AmortizacionFrances {
    public List<Cuota> generarCuotas(Double valor, Double tasa, Integer plazo, Integer idPlan){
        Double cuota= valor / ((1-Math.pow(1+tasa/100, -plazo))/(tasa/100));
        System.out.println(cuota);
        List<Cuota> cuotas =new ArrayList<Cuota>();
        for (int i=0; i<plazo; i++){
            Double interes = valor * tasa/100;
            Double amortizacion=cuota-interes;
            valor-=amortizacion;
            
            Cuota c=new Cuota();
            c.setNumeroCuota(i+1);
            c.setIntereses(interes);
            c.setCapital(amortizacion);
            c.setTotal(cuota);
            c.setSaldo(valor);
            c.setIdPlan(idPlan);
            
            cuotas.add(c);
            
        }
        
        return cuotas;
    }
    
    public static void main(String args[]){
        List<Cuota> cuotas=new AmortizacionFrances().generarCuotas(500000.0, 5.0, 10, 1);
        for (Cuota c:cuotas)
            System.out.println(c);
    }
    
}
