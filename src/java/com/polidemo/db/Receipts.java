/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polidemo.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author x
 */
@Entity
@Table(name = "receipts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receipts.findAll", query = "SELECT r FROM Receipts r"),
    @NamedQuery(name = "Receipts.findByAmountPaid", query = "SELECT r FROM Receipts r WHERE r.amountPaid = :amountPaid"),
    @NamedQuery(name = "Receipts.findByBankReceipt", query = "SELECT r FROM Receipts r WHERE r.bankReceipt = :bankReceipt"),
    @NamedQuery(name = "Receipts.findByBankReceiptDateTime", query = "SELECT r FROM Receipts r WHERE r.bankReceiptDateTime = :bankReceiptDateTime"),
    @NamedQuery(name = "Receipts.findByCountryCode", query = "SELECT r FROM Receipts r WHERE r.countryCode = :countryCode"),
    @NamedQuery(name = "Receipts.findByCountryName", query = "SELECT r FROM Receipts r WHERE r.countryName = :countryName"),
    @NamedQuery(name = "Receipts.findByCurrencyCode", query = "SELECT r FROM Receipts r WHERE r.currencyCode = :currencyCode"),
    @NamedQuery(name = "Receipts.findByCurrencyName", query = "SELECT r FROM Receipts r WHERE r.currencyName = :currencyName"),
    @NamedQuery(name = "Receipts.findByEndDateTime", query = "SELECT r FROM Receipts r WHERE r.endDateTime = :endDateTime"),
    @NamedQuery(name = "Receipts.findByErrorCode", query = "SELECT r FROM Receipts r WHERE r.errorCode = :errorCode"),
    @NamedQuery(name = "Receipts.findByEstablishedDateTime", query = "SELECT r FROM Receipts r WHERE r.establishedDateTime = :establishedDateTime"),
    @NamedQuery(name = "Receipts.findByFinancialInstitutionCode", query = "SELECT r FROM Receipts r WHERE r.financialInstitutionCode = :financialInstitutionCode"),
    @NamedQuery(name = "Receipts.findByFinancialInstitutionCountryCode", query = "SELECT r FROM Receipts r WHERE r.financialInstitutionCountryCode = :financialInstitutionCountryCode"),
    @NamedQuery(name = "Receipts.findByFinancialInstitutionName", query = "SELECT r FROM Receipts r WHERE r.financialInstitutionName = :financialInstitutionName"),
    @NamedQuery(name = "Receipts.findByMerchantAcctName", query = "SELECT r FROM Receipts r WHERE r.merchantAcctName = :merchantAcctName"),
    @NamedQuery(name = "Receipts.findByMerchantAcctNumber", query = "SELECT r FROM Receipts r WHERE r.merchantAcctNumber = :merchantAcctNumber"),
    @NamedQuery(name = "Receipts.findByMerchantAcctSortCode", query = "SELECT r FROM Receipts r WHERE r.merchantAcctSortCode = :merchantAcctSortCode"),
    @NamedQuery(name = "Receipts.findByMerchantAcctSuffix", query = "SELECT r FROM Receipts r WHERE r.merchantAcctSuffix = :merchantAcctSuffix"),
    @NamedQuery(name = "Receipts.findByMerchantEstablishedDateTime", query = "SELECT r FROM Receipts r WHERE r.merchantEstablishedDateTime = :merchantEstablishedDateTime"),
    @NamedQuery(name = "Receipts.findByMerchantReference", query = "SELECT r FROM Receipts r WHERE r.merchantReference = :merchantReference"),
    @NamedQuery(name = "Receipts.findByPaymentAmount", query = "SELECT r FROM Receipts r WHERE r.paymentAmount = :paymentAmount"),
    @NamedQuery(name = "Receipts.findByStartDateTime", query = "SELECT r FROM Receipts r WHERE r.startDateTime = :startDateTime"),
    @NamedQuery(name = "Receipts.findByTransactionID", query = "SELECT r FROM Receipts r WHERE r.transactionID = :transactionID"),
    @NamedQuery(name = "Receipts.findByTransactionRefNo", query = "SELECT r FROM Receipts r WHERE r.transactionRefNo = :transactionRefNo"),
    @NamedQuery(name = "Receipts.findReceipt", query = "SELECT r FROM Receipts r WHERE r.transactionRefNo = :transactionRefNo and r.merchantReference = :merchantReference")})

public class Receipts implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AmountPaid", precision = 12)
    private Float amountPaid;
    @Column(name = "BankReceipt", length = 50)
    private String bankReceipt;
    @Column(name = "BankReceiptDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bankReceiptDateTime;
    @Column(name = "CountryCode", length = 3)
    private String countryCode;
    @Column(name = "CountryName", length = 30)
    private String countryName;
    @Column(name = "CurrencyCode", length = 3)
    private String currencyCode;
    @Column(name = "CurrencyName", length = 30)
    private String currencyName;
    @Column(name = "EndDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;
    @Column(name = "ErrorCode", length = 10)
    private String errorCode;
    @Lob
    @Column(name = "ErrorMessage", length = 65535)
    private String errorMessage;
    @Column(name = "EstablishedDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date establishedDateTime;
    @Column(name = "FinancialInstitutionCode", length = 10)
    private String financialInstitutionCode;
    @Column(name = "FinancialInstitutionCountryCode", length = 3)
    private String financialInstitutionCountryCode;
    @Column(name = "FinancialInstitutionName", length = 30)
    private String financialInstitutionName;
    @Column(name = "MerchantAcctName", length = 30)
    private String merchantAcctName;
    @Column(name = "MerchantAcctNumber", length = 20)
    private String merchantAcctNumber;
    @Column(name = "MerchantAcctSortCode", length = 10)
    private String merchantAcctSortCode;
    @Column(name = "MerchantAcctSuffix", length = 30)
    private String merchantAcctSuffix;
    @Lob
    @Column(name = "MerchantDefinedData", length = 65535)
    private String merchantDefinedData;
    @Column(name = "MerchantEstablishedDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date merchantEstablishedDateTime;
    @Column(name = "MerchantReference", length = 30)
    private String merchantReference;
    @Basic(optional = false)
    @Column(name = "PaymentAmount", nullable = false)
    private float paymentAmount;
    @Column(name = "StartDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Id
    @Basic(optional = false)
    @Column(name = "TransactionID", nullable = false, length = 40)
    private String transactionID;
    @Basic(optional = false)
    @Column(name = "TransactionRefNo", nullable = false, length = 12)
    private String transactionRefNo;

    public Receipts() {
    }

    public Receipts(String transactionID) {
        this.transactionID = transactionID;
    }

    public Receipts(String transactionID, float paymentAmount, String transactionRefNo) {
        this.transactionID = transactionID;
        this.paymentAmount = paymentAmount;
        this.transactionRefNo = transactionRefNo;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getBankReceipt() {
        return bankReceipt;
    }

    public void setBankReceipt(String bankReceipt) {
        this.bankReceipt = bankReceipt;
    }

    public Date getBankReceiptDateTime() {
        return bankReceiptDateTime;
    }

    public void setBankReceiptDateTime(Date bankReceiptDateTime) {
        this.bankReceiptDateTime = bankReceiptDateTime;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getEstablishedDateTime() {
        return establishedDateTime;
    }

    public void setEstablishedDateTime(Date establishedDateTime) {
        this.establishedDateTime = establishedDateTime;
    }

    public String getFinancialInstitutionCode() {
        return financialInstitutionCode;
    }

    public void setFinancialInstitutionCode(String financialInstitutionCode) {
        this.financialInstitutionCode = financialInstitutionCode;
    }

    public String getFinancialInstitutionCountryCode() {
        return financialInstitutionCountryCode;
    }

    public void setFinancialInstitutionCountryCode(String financialInstitutionCountryCode) {
        this.financialInstitutionCountryCode = financialInstitutionCountryCode;
    }

    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName;
    }

    public String getMerchantAcctName() {
        return merchantAcctName;
    }

    public void setMerchantAcctName(String merchantAcctName) {
        this.merchantAcctName = merchantAcctName;
    }

    public String getMerchantAcctNumber() {
        return merchantAcctNumber;
    }

    public void setMerchantAcctNumber(String merchantAcctNumber) {
        this.merchantAcctNumber = merchantAcctNumber;
    }

    public String getMerchantAcctSortCode() {
        return merchantAcctSortCode;
    }

    public void setMerchantAcctSortCode(String merchantAcctSortCode) {
        this.merchantAcctSortCode = merchantAcctSortCode;
    }

    public String getMerchantAcctSuffix() {
        return merchantAcctSuffix;
    }

    public void setMerchantAcctSuffix(String merchantAcctSuffix) {
        this.merchantAcctSuffix = merchantAcctSuffix;
    }

    public String getMerchantDefinedData() {
        return merchantDefinedData;
    }

    public void setMerchantDefinedData(String merchantDefinedData) {
        this.merchantDefinedData = merchantDefinedData;
    }

    public Date getMerchantEstablishedDateTime() {
        return merchantEstablishedDateTime;
    }

    public void setMerchantEstablishedDateTime(Date merchantEstablishedDateTime) {
        this.merchantEstablishedDateTime = merchantEstablishedDateTime;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionRefNo() {
        return transactionRefNo;
    }

    public void setTransactionRefNo(String transactionRefNo) {
        this.transactionRefNo = transactionRefNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionID != null ? transactionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receipts)) {
            return false;
        }
        Receipts other = (Receipts) object;
        if ((this.transactionID == null && other.transactionID != null) || (this.transactionID != null && !this.transactionID.equals(other.transactionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.polidemo.db.Receipts[ transactionID=" + transactionID + " ]";
    }
    
}
