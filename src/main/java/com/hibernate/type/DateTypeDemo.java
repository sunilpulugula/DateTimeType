package com.hibernate.type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.type.model.Person;
import com.hibernate.type.usertype.DateTimeType;

/**
 * Created by sunilp on 13/7/15.
 */
public class DateTypeDemo {

    public static void main(String args[]) throws Exception
    {

        Session session = getSession();

        Person person = new Person();
        person.setId(27);
        person.setDob(null);

        Transaction tx= session.beginTransaction();
        session.save(person);
        tx.commit();

        session.close();

    }

    private static Session getSession()
    {
        Configuration configuration = new Configuration();

        // Pass hibernate configuration file
        configuration.configure("hibernate.cfg.xml");

        DateTimeType dateTimeType = new DateTimeType();

        configuration.registerTypeOverride(dateTimeType,new String[] { dateTimeType.returnedClass().getName() });

        // Since version 4.x, service registry is being used
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties()).build();

        // Create session factory instance
        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);


        // Get current session
        return factory.openSession();

    }
}
