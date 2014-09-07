package com.del.cft.test.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


/**
 * User: johni5
 * Date: 04.09.14
 * Time: 18:06
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        try {
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
