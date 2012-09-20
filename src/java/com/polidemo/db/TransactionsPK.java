/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author x
 */
@Embeddable
public class TransactionsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "orderno", nullable = false, length = 50)
    private String orderno;
    @Basic(optional = false)
    @Column(name = "refno", nullable = false, length = 12)
    private String refno;

    public TransactionsPK() {
    }

    public TransactionsPK(String orderno, String refno) {
        this.orderno = orderno;
        this.refno = refno;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderno != null ? orderno.hashCode() : 0);
        hash += (refno != null ? refno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionsPK)) {
            return false;
        }
        TransactionsPK other = (TransactionsPK) object;
        if ((this.orderno == null && other.orderno != null) || (this.orderno != null && !this.orderno.equals(other.orderno))) {
            return false;
        }
        if ((this.refno == null && other.refno != null) || (this.refno != null && !this.refno.equals(other.refno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polidemo.db.TransactionsPK[ orderno=" + orderno + ", refno=" + refno + " ]";
    }
    
}
