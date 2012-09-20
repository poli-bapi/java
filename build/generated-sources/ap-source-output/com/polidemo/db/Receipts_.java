package com.polidemo.db;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-09-20T07:43:15")
@StaticMetamodel(Receipts.class)
public class Receipts_ { 

    public static volatile SingularAttribute<Receipts, String> errorMessage;
    public static volatile SingularAttribute<Receipts, String> financialInstitutionCountryCode;
    public static volatile SingularAttribute<Receipts, String> countryName;
    public static volatile SingularAttribute<Receipts, Date> startDateTime;
    public static volatile SingularAttribute<Receipts, String> financialInstitutionName;
    public static volatile SingularAttribute<Receipts, Date> bankReceiptDateTime;
    public static volatile SingularAttribute<Receipts, Date> establishedDateTime;
    public static volatile SingularAttribute<Receipts, String> merchantDefinedData;
    public static volatile SingularAttribute<Receipts, Float> paymentAmount;
    public static volatile SingularAttribute<Receipts, String> currencyName;
    public static volatile SingularAttribute<Receipts, Date> merchantEstablishedDateTime;
    public static volatile SingularAttribute<Receipts, String> transactionRefNo;
    public static volatile SingularAttribute<Receipts, String> merchantAcctName;
    public static volatile SingularAttribute<Receipts, String> merchantAcctSuffix;
    public static volatile SingularAttribute<Receipts, String> financialInstitutionCode;
    public static volatile SingularAttribute<Receipts, String> currencyCode;
    public static volatile SingularAttribute<Receipts, String> merchantAcctNumber;
    public static volatile SingularAttribute<Receipts, String> countryCode;
    public static volatile SingularAttribute<Receipts, String> merchantReference;
    public static volatile SingularAttribute<Receipts, String> transactionID;
    public static volatile SingularAttribute<Receipts, Date> endDateTime;
    public static volatile SingularAttribute<Receipts, String> bankReceipt;
    public static volatile SingularAttribute<Receipts, String> merchantAcctSortCode;
    public static volatile SingularAttribute<Receipts, String> errorCode;
    public static volatile SingularAttribute<Receipts, Float> amountPaid;

}