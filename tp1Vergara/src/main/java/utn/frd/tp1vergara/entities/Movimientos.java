/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.tp1vergara.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Juan Manuel Vergara
 */
@Entity
@Table(name = "movimientos")
@NamedQueries({
    @NamedQuery(name = "Movimientos.findAll", query = "SELECT m FROM Movimientos m"),
    @NamedQuery(name = "Movimientos.findByIdTransaccion", query = "SELECT m FROM Movimientos m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "Movimientos.findByFecha", query = "SELECT m FROM Movimientos m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Movimientos.findByMonto", query = "SELECT m FROM Movimientos m WHERE m.monto = :monto"),
    @NamedQuery(name = "Movimientos.findByIdCuentaOrigen", query = "SELECT m FROM Movimientos m WHERE m.idCuentaOrigen = :idCuentaOrigen"),
    @NamedQuery(name = "Movimientos.findByIdCuentaDestino", query = "SELECT m FROM Movimientos m WHERE m.idCuentaDestino = :idCuentaDestino"),
    @NamedQuery(name = "Movimientos.findByDescripcion", query = "SELECT m FROM Movimientos m WHERE m.descripcion = :descripcion")})
public class Movimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTransaccion")
    private Integer idTransaccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private float monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCuentaOrigen")
    private int idCuentaOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCuentaDestino")
    private int idCuentaDestino;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    public Movimientos() {
    }

    public Movimientos(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Movimientos(Integer idTransaccion, Date fecha, float monto, int idCuentaOrigen, int idCuentaDestino) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDestino = idCuentaDestino;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public void setIdCuentaOrigen(int idCuentaOrigen) {
        this.idCuentaOrigen = idCuentaOrigen;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaccion != null ? idTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientos)) {
            return false;
        }
        Movimientos other = (Movimientos) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utn.frd.tp1vergara.entities.Movimientos[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
