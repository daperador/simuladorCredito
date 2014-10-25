/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.dao.AdministradorDAO;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Administrador;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Fredy
 */
@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginServicio {
    
    
//@Context HttpServletRequest r,
    
    @POST
    public boolean login(Administrador usuario){

        AdministradorDAO dao=new AdministradorDAO();
        Administrador a = dao.login(usuario);
        if (a==null){
            return false;
        }else{
            return a.getPassword().equals(usuario.getPassword());
        }
    }
}
