package com.polidemo.db;

import com.polidemo.db.TransactionsPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-09-19T17:11:16")
@StaticMetamodel(Transactions.class)
public class Transactions_ { 

    public static volatile SingularAttribute<Transactions, Float> amount;
    public static volatile SingularAttribute<Transactions, Short> status;
    public static volatile SingularAttribute<Transactions, String> token;
    public static volatile SingularAttribute<Transactions, TransactionsPK> transactionsPK;
    public static volatile SingularAttribute<Transactions, String> currency;

}