package org.oz.persistence.dao.db1;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by <a href="https://twitter.com/jaehoox">jaehoo</a> on 16/03/2018
 */
@Repository
public class CustomerDao {

    public static final String SEL_TABLES="select.tablesh2";

    @PersistenceContext(unitName = "unit1" , type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Collection loadCustomers() {
        Query query = em.createQuery("FROM Customer");
        return query.getResultList();

    }

    public Collection getTables(){
        return em.createNamedQuery(SEL_TABLES).getResultList();
    }

}
