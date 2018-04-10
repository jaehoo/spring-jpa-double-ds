package org.oz.persistence.dao;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import lombok.Setter;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

/**
 * Created by <a href="https://twitter.com/jaehoox">jaehoo</a> on 10/04/2018
 */
public class AtomikosJtaPlatform extends AbstractJtaPlatform {

    private static final long serialVersionUID = 1L;

    @Setter
    static TransactionManager transactionManager;
    @Setter
    static UserTransaction transaction;



    @Override
    protected TransactionManager locateTransactionManager() {
        return transactionManager;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return transaction;
    }
}