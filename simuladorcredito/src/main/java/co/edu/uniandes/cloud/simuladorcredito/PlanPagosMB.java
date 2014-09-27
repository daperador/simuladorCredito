/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Cuota;
import co.edu.uniandes.cloud.simuladorcredito.jpa.Linea;
import co.edu.uniandes.cloud.simuladorcredito.jpa.PlanPago;
import co.edu.uniandes.cloud.simuladorcredito.persistencia.AdministradorPersistencia;
import co.edu.uniandes.cloud.simuladorcredito.persistencia.PlanPagosPersistencia;
import co.edu.uniandes.cloud.simuladorcredito.util.Constantes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.dialog.Dialog;

/**
 *
 * @author Fredy
 */
@ManagedBean(name = "planPagos")
@SessionScoped
public class PlanPagosMB implements Serializable{
   
    private List<SelectItem> lineas = new ArrayList<SelectItem>();
    private Integer idAdmon=0;
    private PlanPago pp=new PlanPago();
    private int momento=1;
    private List<Linea> ls;
    private List<Cuota> cuotas=new ArrayList<Cuota>();
    
    private List<PlanPago> planesPago;
    
    @EJB
    private AdministradorPersistencia dao;
    
    @PostConstruct
    public void init(){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (req.getParameter("id")!=null){
            pp.setIdLinea(new Linea());
            idAdmon = Integer.parseInt(req.getParameter("id"));
            ls=dao.consultarLineasAdministrador(idAdmon);
            for (Linea l:ls){
                lineas.add(new SelectItem(l.getId(), l.getNombre()));
            }
            
        }
        
    }

    public Integer getIdAdmon() {
        return idAdmon;
    }

    public void setIdAdmon(Integer idAdmon) {
        this.idAdmon = idAdmon;
    }

    public PlanPago getPp() {
        return pp;
    }

    public void setPp(PlanPago pp) {
        this.pp = pp;
    }

    public int getMomento() {
        return momento;
    }

    public void setMomento(int momento) {
        this.momento = momento;
    }
    
    public void accion(){
        if (momento==1){
            pp.setEstado(Constantes.ESTADO_EN_PROCESO);
            pp.setFechaCreacion(new Date());
            pp=this.dao.guardarPlanPagos(pp);
            momento=2;
            System.out.println(pp.getId());
        }else{
            pp=this.dao.consultarPlanPago(pp.getId());
            if (pp.getEstado().equals(Constantes.ESTADO_GENERADO)){
                cuotas=dao.consultarCuotas(pp);    
            }
        }
    }

    public List<SelectItem> getLineas() {
        return lineas;
    }

    public void setLineas(List<SelectItem> lineas) {
        this.lineas = lineas;
    }
    
    public boolean isReadOnly(){
        return this.momento == 2;
    }
    
    public boolean isMostrarMensaje(){
        return this.isReadOnly() && this.pp.getEstado()!=null && this.pp.getEstado().equals(Constantes.ESTADO_EN_PROCESO);
    }
    
    public boolean isMostrarCuotas(){
        return this.isReadOnly() && this.pp.getEstado()!=null && this.pp.getEstado().equals(Constantes.ESTADO_GENERADO);
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    @EJB
    private PlanPagosPersistencia planPagosPersistencia;
    
    public List<PlanPago> getPlanesPago() {
        String usuario=((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("usuario").toString();
        planesPago=planPagosPersistencia.getPlanesPago(usuario);
        return planesPago;
    }

    public void setPlanesPago(List<PlanPago> planesPago) {
        this.planesPago = planesPago;
    }
    
    private PlanPago detalle;
    private Dialog panelDetalle;
    
    public void detalle(PlanPago planPago){
        detalle=planPago;
        detalle=this.dao.consultarPlanPago(planPago.getId());
        if (detalle.getEstado().equals(Constantes.ESTADO_GENERADO)){
            detalle.setCuotas(dao.consultarCuotas(detalle));
        }
        panelDetalle.setVisible(true);
    }

    public PlanPago getDetallePlanPago() {
        return detalle;
    }

    public void setDetallePlanPago(PlanPago detalle) {
        this.detalle = detalle;
    }

    public Dialog getPanelDetalle() {
        return panelDetalle;
    }

    public void setPanelDetalle(Dialog panelDetalle) {
        this.panelDetalle = panelDetalle;
    }
}
