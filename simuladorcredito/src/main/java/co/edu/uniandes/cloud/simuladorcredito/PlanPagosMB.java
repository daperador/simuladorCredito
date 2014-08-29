/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Linea;
import co.edu.uniandes.cloud.simuladorcredito.jpa.PlanPago;
import co.edu.uniandes.cloud.simuladorcredito.persistencia.AdministradorPersistencia;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fredy
 */
@ManagedBean(name = "planPagos")
@SessionScoped
public class PlanPagosMB {
   
    private List<SelectItem> lineas = new ArrayList<SelectItem>();
    private Integer idAdmon=0;
    private PlanPago pp=new PlanPago();
    private int momento=1;
    private List<Linea> ls;
    
    @EJB
    private AdministradorPersistencia dao;
    
    @PostConstruct
    public void init(){
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (req.getParameter("id")!=null){
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
        
    }

    public List<SelectItem> getLineas() {
        return lineas;
    }

    public void setLineas(List<SelectItem> lineas) {
        this.lineas = lineas;
    }
    
    
}
