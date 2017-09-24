package javaConfig.spring.common;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(env.getProperty("dataSource.url"));
        ds.setDriverClassName(env.getProperty("dataSource.driverClassName"));
        ds.setUsername(env.getProperty("dataSource.username"));
        ds.setPassword(env.getProperty("dataSource.password"));
        ds.setInitialSize(Integer.parseInt(env.getProperty("dataSource.initialSize")));
        ds.setMaxIdle(Integer.parseInt(env.getProperty("dataSource.maxIdle")));
        ds.setMinIdle(Integer.parseInt(env.getProperty("dataSource.minIdle")));
        ds.setMaxTotal(Integer.parseInt(env.getProperty("dataSource.maxTotal")));
        return ds;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setShowSql(true);
        va.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        va.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPersistenceUnitName("issue-tracker-unit");
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("domain.*");
        emf.setJpaVendorAdapter(va);

        return emf;
    }

    @Bean
    public SharedEntityManagerBean entityManager(EntityManagerFactory emf) {
        SharedEntityManagerBean em = new SharedEntityManagerBean();
        em.setEntityManagerFactory(emf);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }


}
