package org.guru;

import org.guru.controller.CarAutomationFrameController;
import org.guru.model.Car;
import org.guru.hibernate.HibernateUtil;
import org.hibernate.Session;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Car car = new Car();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.merge(car);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CarAutomationFrameController().execute();
            }
        });


    }
}