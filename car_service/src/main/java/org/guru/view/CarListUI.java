package org.guru.view;


import org.guru.model.Car;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class CarListUI {

    public static JPanel initPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Aramak istediğiniz araç licensePlatesını giriniz."));
        JPanel up = new JPanel(new GridLayout(4,2));

        JLabel idLabel = new JLabel("licensePlate", JLabel.LEFT);
        up.add(idLabel);


        JTextField licensePlateField = new JTextField();
        up.add(licensePlateField);

        JList<Object> stockList = new JList<Object> ();
        stockList.setListData(new Car().getAllSqlSentence().toArray());


        panel.add(up,BorderLayout.NORTH);
        licensePlateField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                    stockList.setListData(new Car().listSqlSentence(licensePlateField.getText()).toArray());
                    stockList.setSelectedIndex(0);
            }
        });

            return panel;
    }

}
