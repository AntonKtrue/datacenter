package com.vaadin.demo.dashboard.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;

/**
 * Created by Anton on 09.01.2017.
 */
public class DaoSession {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            setUp();
        }
        return sessionFactory;
    }

    private static void setUp() {
        File configFile = new File("hibernate.cfg.xml");
        System.out.println("File config:" + configFile.toString());
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(new File("hibernate.cfg.xml"))
                //.configure(DaoSession.class.getResource("hibernate.cfg.xml").getFile())
                .build();
        System.out.println("Configuration complete");
        System.out.println("registry: " + registry);
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
