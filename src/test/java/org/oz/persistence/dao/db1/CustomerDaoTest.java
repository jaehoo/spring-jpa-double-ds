package org.oz.persistence.dao.db1;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.oz.persistence.dao.db1.model.Customer;
import org.oz.persistence.dao.db2.ProductDao;
import org.oz.persistence.dao.db2.model.Product;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:app-ctx-test.xml"})
@TransactionConfiguration
@Slf4j
public class CustomerDaoTest {


    @Resource(name = "customerDao")
    private CustomerDao customerDao;

    @Resource(name = "productDao")
    private ProductDao productDao;



    @Test
    @Transactional("transactionManager1")
    public void loadCustomers() throws Exception {

        List<Customer> customers = (List<Customer>) customerDao.loadCustomers();

        log.info("customers:{}",customers.size());

        for(Customer c : customers){
            log.info("{}",c);
        }


        List tables = (List<Product>) customerDao.getTables();

        log.info("tables:{}",tables.size());
        for(Object c : tables){
            log.info("{}",c);
        }


    }

    @Test
    @Transactional("transactionManager2")
    public void loadProducts() throws Exception {

        List<Product> products = (List<Product>) productDao.loadProducts();

        log.info("products:{}",products.size());
        log.info("{}",products.get(0));


        List tables = (List<Product>) productDao.getTables();

        log.info("tables:{}",tables.size());
        for(Object c : tables){
            log.info("{}",c);
        }


    }


    @Test
    public void name() throws Exception {
        List tables = (List) productDao.getTables();
        tables.addAll((List) customerDao.getTables());

        log.info("tables:{}",tables.size());
        for(Object c : tables){
            log.info("{}",c);
        }

    }



}

