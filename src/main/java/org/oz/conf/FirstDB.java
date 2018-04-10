package org.oz.conf;

import org.oz.persistence.dao.db1.CustomerDao;
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
//@DependsOn("transactionManager")
@EnableJpaRepositories(
        basePackages = "org.oz.persistence.dao.db1.model",
        entityManagerFactoryRef = "entityManagerFactory1",
        transactionManagerRef = "transactionManager1"
)
public class FirstDB {

    @Autowired
    private Environment env;


    @Primary
        @Bean(name = "dataSource1")
    public DataSource getDataSource() {

        DriverManagerDataSource dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc1.driver"));
        dataSource.setUrl(env.getProperty("jdbc1.url"));
        dataSource.setUsername(env.getProperty("jdbc1.user"));
        dataSource.setPassword(env.getProperty("jdbc1.pass"));

        return dataSource;
    }

    @Primary
    @Bean(name = "entityManagerFactory1")
    public LocalContainerEntityManagerFactoryBean getEntityManager() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setPersistenceXmlLocation(env.getProperty("persistenceXmlLocation"));
        em.setDataSource(getDataSource());
        em.setPackagesToScan(new String[] {"org.oz.persistence.dao.db1" });
        em.setPersistenceUnitName(env.getProperty("persitence.unit1"));

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Primary
    @Bean(name = "transactionManager1")
    public PlatformTransactionManager getTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManager().getObject());
        return transactionManager;
    }

    @Bean(name = "customerDao")
    public CustomerDao getCustoerDao(){
        return new CustomerDao();
    }
}
