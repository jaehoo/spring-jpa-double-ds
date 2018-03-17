package org.oz.persistence.dao.db2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by <a href="https://twitter.com/jaehoox">jaehoo</a> on 16/03/2018
 */
public class ProductDao {

    public static final String SEL_TABLES="select.tablesh2";

    @PersistenceContext(unitName = "unit2", type = PersistenceContextType.TRANSACTION, name = "unit2")
    private EntityManager em;

    public Collection loadProducts() {
        Query query = em.createQuery("FROM Product");
        return query.getResultList();

    }

    public Collection getTables(){
        return em.createNamedQuery(SEL_TABLES).getResultList();
    }


}
