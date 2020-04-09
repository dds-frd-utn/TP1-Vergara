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
import utn.frd.tp1vergara.entities.Clientes;
import utn.frd.tp1vergara.sessions.ClientesFacade;

/**
 *
 * @author Sergio
 */
@Path("/clientes")
public class ClientesRest {
    @EJB
    private ClientesFacade ejbClientesFacade;
    
    //obtener todas las entidades
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Clientes> findAll(){
        return ejbClientesFacade.findAll();
    }
    
    //crear entidades
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Clientes clientes){
        ejbClientesFacade.create(clientes);
    }
    
    //actualizar entidades
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public void edit(@PathParam("id")long id, Clientes clientes){
        ejbClientesFacade.edit(clientes);
    }
    
    //eliminar entidades
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("/{id}")
    public void remove(@PathParam("id")long id){
        ejbClientesFacade.remove( ejbClientesFacade.find(id) );
    }
    
    //obtener una entidad por id
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Clientes findById(@PathParam("id")long id){
        return ejbClientesFacade.find(id);
    }
}

