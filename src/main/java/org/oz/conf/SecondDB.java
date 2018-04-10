package org.oz.conf;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.h2.jdbcx.JdbcDataSource;
import org.oz.persistence.dao.db1.CustomerDao;
import org.oz.persistence.dao.db2.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by <a href="https://twitter.com/jaehoox">jaehoo</a> on 10/04/2018
 */

@Configuration
@PropertySource({ "classpath:app-props.properties" })
@DependsOn("transactionManager")
@EnableJpaRepositories(
        basePackages = "org.oz.persistence.dao.db2.model",
        entityManagerFactoryRef = "entityManagerFactory2",
        transactionManagerRef = "transactionManager"
)
public class SecondDB {

    @Autowired
    private Environment env;


    @Primary
    @Bean(name = "dataSource2")
    @Lazy
    public DataSource getDataSource() {

        JdbcDataSource dataSource= new JdbcDataSource();
        dataSource.setURL(env.getProperty("jdbc2.url"));
        dataSource.setUser(env.getProperty("jdbc2.user"));
        dataSource.setPassword(env.getProperty("jdbc2.pass"));

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(dataSource);
        xaDataSource.setUniqueResourceName("xads2");

        return dataSource;
    }

    @Primary
    @Bean(name = "entityManagerFactory2")
    @DependsOn("transactionManager")
    @Lazy
    public LocalContainerEntityManagerFactoryBean getEntityManager() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setPersistenceXmlLocation(env.getProperty("persistenceXmlLocation"));
        //em.setDataSource(getDataSource());
        em.setPackagesToScan(new String[] {"org.oz.persistence.dao.db2" });
        em.setPersistenceUnitName(env.getProperty("persitence.unit2"));
        em.setJtaDataSource(getDataSource());


        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

//    @Primary
//    @Bean(name = "transactionManager2")
//    @Lazy
//    public PlatformTransactionManager getTransactionManager() {
//
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(getEntityManager().getObject());
//        return transactionManager;
//    }

    @Bean(name = "productDao")
    public ProductDao getProductDao(){
        return new ProductDao();
    }
}
