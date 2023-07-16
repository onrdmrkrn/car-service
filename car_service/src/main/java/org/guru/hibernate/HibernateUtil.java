package org.guru.hibernate;

import org.guru.model.Car;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        try{
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Car.class);

            SessionFactory sessionFactory1 = cfg.configure("hibernatecfg.xml").buildSessionFactory();
            return sessionFactory1;
        }
        catch (Exception e){
            System.out.println("Session dont created.");
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
