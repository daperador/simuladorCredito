/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.cloud.simuladorcredito;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Administrador;
import co.edu.uniandes.cloud.simuladorcredito.persistencia.AdministradorPersistencia;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.dialog.Dialog;

/**
 *
 * @author Daniel
 */
@ManagedBean
@SessionScoped
public class RegistroAdministrador implements Serializable{
    private Administrador administrador=new Administrador();
    private String contrasena;
    private String url;
    private Dialog confirmacion;
    private Dialog registro;
    private Dialog ingreso;
    
    private String login;
    private String password;
    
    @EJB
    private AdministradorPersistencia administradorPersistencia;
    
    public void guardarAdministrador(){
        FacesContext context = FacesContext.getCurrentInstance();
        //validar que el correo no fuera asignado anteriormente
        boolean error=false;
        if(administradorPersistencia.consultarAdministradorCorreo(administrador.getEmail())!=null){
            context.addMessage("email", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  "Este correo ya fue registrado anteriormente"));
            error=true;
        }
        if(contrasena.compareTo(administrador.getContrasena())!=0){
            context.addMessage("contrasena", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  "La contraseña no coincide"));
            error=true;
        }
        if(!error){
            administradorPersistencia.crearAdministrador(administrador);
            //traer la url
            
            url=context.getExternalContext().getRequestScheme()+"://"+
                    context.getExternalContext().getRequestServerName()+":"+
                    context.getExternalContext().getRequestServerPort()+"/"+
                    context.getExternalContext().getRequestContextPath()+administrador.getUrl();
            administrador=new Administrador();
            confirmacion.setVisible(true);
            registro.setVisible(false);
        }
    }
    
    public String ingresar(){
        boolean loggedin = administradorPersistencia.login(login,password);
        if(loggedin){
            ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().setAttribute("usuario", login);
            return "administracion/linea.xhtml";
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("contrasena", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",  "El usuario o la contraseña no es válida"));
        }
        return null;
    }
    
    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Dialog getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(Dialog confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Dialog getRegistro() {
        return registro;
    }

    public void setRegistro(Dialog registro) {
        this.registro = registro;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dialog getIngreso() {
        return ingreso;
    }

    public void setIngreso(Dialog ingreso) {
        this.ingreso = ingreso;
    }
    
    
    
}
