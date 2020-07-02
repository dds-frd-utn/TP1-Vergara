/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.tp1vergara.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utn.frd.tp1vergara.entities.Movimientos;

/**
 *
 * @author Juan Manuel Vergara
 */
@Stateless
public class MovimientosFacade extends AbstractFacade<Movimientos> {

    @PersistenceContext(unitName = "jdbc/dds")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientosFacade() {
        super(Movimientos.class);
    }
    
}
