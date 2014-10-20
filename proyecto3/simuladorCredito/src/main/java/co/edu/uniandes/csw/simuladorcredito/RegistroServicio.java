/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.dto.RegistroDTO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel
 */
@Path("/registro")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistroServicio {
    
    @POST
    public RegistroDTO registrarUsuario(RegistroDTO registro){
        registro.setId(1L);
        return registro;
    }
    
}
