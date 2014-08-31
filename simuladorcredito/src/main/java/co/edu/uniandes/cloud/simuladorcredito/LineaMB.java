/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Administrador;
import co.edu.uniandes.cloud.simuladorcredito.jpa.Linea;
import co.edu.uniandes.cloud.simuladorcredito.persistencia.AdministradorPersistencia;
import co.edu.uniandes.cloud.simuladorcredito.persistencia.LineaPersistencia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Daniel
 */
@ManagedBean
@SessionScoped
public class LineaMB implements Serializable{
    
    private Linea linea;
    private List<Linea> lineas=new ArrayList<Linea>();
    private boolean editar=false;
    private Administrador administrador;
    
    @EJB
    private LineaPersistencia lineaPersistencia;
    
    @EJB
    private AdministradorPersistencia administradorPersistencia;
    
    @PostConstruct
    public void init(){
        String login=(String)((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("usuario");
        administrador=administradorPersistencia.consultarAdministradorCorreo(login);
    } 
    
    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public List<Linea> getLineas() {
        if(lineas==null || lineas.size()==0){
            lineas=lineaPersistencia.getLineas(administrador);
        }
        return lineas;
    }

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }
    
    public void nuevaLinea(){
        editar=true;
        linea=new Linea();
    }
    
    public void guardar(){
        String usuario=((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("usuario").toString();
        linea.setIdAdmon(administradorPersistencia.consultarAdministradorCorreo(usuario));
        lineaPersistencia.actualizarLinea(linea);
        lineas=null;
        editar=false;
    }
    
    public void cancelar(){
        editar=false;
    }
    
    public void actualizar(Integer id){
        linea=lineaPersistencia.getLinea(id);
        editar=true;
    }
    
    public void borrar(Integer id){
        try{
            lineaPersistencia.borrarLinea(id);
            lineas=null;
        }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se puede eliminar. Tiene asociados planes de pago.", null));
        }
    }
}
