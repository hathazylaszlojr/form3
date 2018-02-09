package com.form3.hometest.configuration;

import com.form3.hometest.entity.Account;
import com.form3.hometest.entity.Charge;
import com.form3.hometest.entity.Payment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class PersistenceConfiguration {

    @Bean
    public SessionFactory sessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

        sessionBuilder.addAnnotatedClasses(Account.class);
        sessionBuilder.addAnnotatedClasses(Payment.class);
        sessionBuilder.addAnnotatedClasses(Charge.class);

        return sessionBuilder.buildSessionFactory();
    }


}
