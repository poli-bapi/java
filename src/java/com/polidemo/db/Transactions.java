/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo.db;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author x
 */
@Entity
@Table(name = "transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByOrderno", query = "SELECT t FROM Transactions t WHERE t.transactionsPK.orderno = :orderno"),
    @NamedQuery(name = "Transactions.findByRefno", query = "SELECT t FROM Transactions t WHERE t.transactionsPK.refno = :refno"),
    @NamedQuery(name = "Transactions.findByAmount", query = "SELECT t FROM Transactions t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transactions.findByCurrency", query = "SELECT t FROM Transactions t WHERE t.currency = :currency"),
    @NamedQuery(name = "Transactions.findByStatus", query = "SELECT t FROM Transactions t WHERE t.status = :status"),
    @NamedQuery(name = "Transactions.findByToken", query = "SELECT t FROM Transactions t WHERE t.token = :token")})
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransactionsPK transactionsPK;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private float amount;
    @Basic(optional = false)
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;
    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    private short status;
    @Basic(optional = false)
    @Column(name = "token", nullable = false, length = 50)
    private String token;

    public Transactions() {
    }

    public Transactions(TransactionsPK transactionsPK) {
        this.transactionsPK = transactionsPK;
    }

    public Transactions(TransactionsPK transactionsPK, float amount, String currency, short status, String token) {
        this.transactionsPK = transactionsPK;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.token = token;
    }

    public Transactions(String orderno, String refno) {
        this.transactionsPK = new TransactionsPK(orderno, refno);
    }

    public TransactionsPK getTransactionsPK() {
        return transactionsPK;
    }

    public void setTransactionsPK(TransactionsPK transactionsPK) {
        this.transactionsPK = transactionsPK;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionsPK != null ? transactionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionsPK == null && other.transactionsPK != null) || (this.transactionsPK != null && !this.transactionsPK.equals(other.transactionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polidemo.db.Transactions[ transactionsPK=" + transactionsPK + " ]";
    }
    
}
