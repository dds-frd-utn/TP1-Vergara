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

/**
 *
 * @author Juan Manuel Vergara
 */
@Entity
@Table(name = "transacciones")
@NamedQueries({
    @NamedQuery(name = "Transacciones.findAll", query = "SELECT t FROM Transacciones t"),
    @NamedQuery(name = "Transacciones.findByIdTransaccion", query = "SELECT t FROM Transacciones t WHERE t.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "Transacciones.findByFecha", query = "SELECT t FROM Transacciones t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Transacciones.findByMonto", query = "SELECT t FROM Transacciones t WHERE t.monto = :monto"),
    @NamedQuery(name = "Transacciones.findByPorcentajeImpuestos", query = "SELECT t FROM Transacciones t WHERE t.porcentajeImpuestos = :porcentajeImpuestos"),
    @NamedQuery(name = "Transacciones.findByIdCuentaOrigen", query = "SELECT t FROM Transacciones t WHERE t.idCuentaOrigen = :idCuentaOrigen"),
    @NamedQuery(name = "Transacciones.findByIdCuentaDestino", query = "SELECT t FROM Transacciones t WHERE t.idCuentaDestino = :idCuentaDestino"),
    @NamedQuery(name = "Transacciones.findByTotalTransferido", query = "SELECT t FROM Transacciones t WHERE t.totalTransferido = :totalTransferido")})
public class Transacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTransaccion")
    private Integer idTransaccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private float monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentajeImpuestos")
    private float porcentajeImpuestos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCuentaOrigen")
    private int idCuentaOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCuentaDestino")
    private int idCuentaDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalTransferido")
    private float totalTransferido;

    public Transacciones() {
    }

    public Transacciones(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Transacciones(Integer idTransaccion, Date fecha, float monto, float porcentajeImpuestos, int idCuentaOrigen, int idCuentaDestino, float totalTransferido) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.porcentajeImpuestos = porcentajeImpuestos;
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDestino = idCuentaDestino;
        this.totalTransferido = totalTransferido;
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

    public float getPorcentajeImpuestos() {
        return porcentajeImpuestos;
    }

    public void setPorcentajeImpuestos(float porcentajeImpuestos) {
        this.porcentajeImpuestos = porcentajeImpuestos;
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

    public float getTotalTransferido() {
        return totalTransferido;
    }

    public void setTotalTransferido(float totalTransferido) {
        this.totalTransferido = totalTransferido;
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
        if (!(object instanceof Transacciones)) {
            return false;
        }
        Transacciones other = (Transacciones) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utn.frd.tp1vergara.entities.Transacciones[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
