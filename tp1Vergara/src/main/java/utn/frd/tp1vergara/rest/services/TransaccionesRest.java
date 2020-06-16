/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.tp1vergara.rest.services;

/**
 *
 * @author Juan Manuel Vergara
 */

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utn.frd.tp1vergara.entities.Transacciones;
import utn.frd.tp1vergara.sessions.TransaccionesFacade;

/**
 *
 * @author Sergio
 */
@Path("/transacciones")
public class TransaccionesRest {
    @EJB
    private TransaccionesFacade ejbTransaccionesFacade;
    
    //obtener todas las entidades
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transacciones> findAll(){
        return ejbTransaccionesFacade.findAll();
    }
    
    //crear entidades
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Transacciones Transacciones){
        ejbTransaccionesFacade.create(Transacciones);
    }
    
    //actualizar entidades
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public void edit(@PathParam("id")int id, Transacciones transacciones){
        ejbTransaccionesFacade.edit(transacciones);
    }
    
    //eliminar entidades
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("/{id}")
    public void remove(@PathParam("id")int id){
        ejbTransaccionesFacade.remove( ejbTransaccionesFacade.find(id) );
    }
    
    //obtener una entidad por id
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Transacciones findById(@PathParam("id")int id){
        return ejbTransaccionesFacade.find(id);
    }
}
