package org.guru.view;

import javax.swing.*;
import java.awt.*;

public class ServiceDetailsProcessesUI extends JPanel {
    private static ServiceDetailsProcessesUI serviceDetailsProcessesUI;

    public static ServiceDetailsProcessesUI getInstance() {
        if (serviceDetailsProcessesUI == null) {
            serviceDetailsProcessesUI = new ServiceDetailsProcessesUI();
        }
        return serviceDetailsProcessesUI;
    }
    public JLabel carLicensePlateLabel = new JLabel("Araç Plakası:", JLabel.LEFT);
    public JTextField carLicensePlateField = new JTextField();
    public JLabel serviceDetailIdField = new JLabel("Servis Detay Numarası:", JLabel.LEFT);
    public JTextField serviceDetailIdLabel = new JTextField();
    public JLabel operationToBePerformedLabel = new JLabel("Yapılacak İşlem:", JLabel.LEFT);

    public JTextField operationToBePerformedField = new JTextField();
    public JLabel serviceCostLabel = new JLabel("Ücreti:", JLabel.LEFT);
    public JTextField serviceHistoryIdField = new JTextField();
    public JLabel serviceHistoryIdLabel = new JLabel("Servis Kaydının ID'si:", JLabel.LEFT);

    public JTextField serviceCostField = new JTextField();

    public JButton saveButton = new JButton("İşlem Kaydet");

    public JButton updateButton = new JButton("İşlem Güncelle");

    public JButton findButton = new JButton("İşlem Ara");

    public JButton deleteButton = new JButton("İşlem Sil");


    public ServiceDetailsProcessesUI() {
        initPanel();
    }
    public void initPanel() {
        setLayout(new GridLayout(25,8));
        add(carLicensePlateLabel);
        add(carLicensePlateField);
        add(operationToBePerformedLabel);
        add(operationToBePerformedField);
        add(serviceCostLabel);
        add(serviceCostField);
        add(saveButton);
        add(updateButton);
        add(deleteButton);
    }
    public void clear() {
        carLicensePlateField.setText("");
        operationToBePerformedField.setText("");
        serviceCostField.setText("");
        serviceHistoryIdField.setText("");
    }
    public String getOperationToBePerformed() {
        return operationToBePerformedField.getText();
    }

    public String getOperationToBePerformedField() {
        return operationToBePerformedField.getText();
    }

    public Double getServiceCostField() {
        return Double.valueOf(serviceCostField.getText());
    }

    public void setServiceHistoryId(int serviceHistoryId) {
        serviceHistoryIdField.setText(String.valueOf(serviceHistoryId));

    }
    public int getServiceDetailId() {
        return Integer.parseInt(serviceDetailIdField.getText());
    }
    public void setServiceDetailId(int serviceDetailId) {
        serviceHistoryIdField.setText(String.valueOf(serviceDetailId));

    }
    public int getServiceHistoryId() {
        return Integer.parseInt(serviceHistoryIdField.getText());
    }


    public void setOperationToBePerformedField(String operationToBePerformed) {
        operationToBePerformedField.setText(operationToBePerformed);

    }
    public void setServiceCostField(Double serviceCost) {
        serviceCostField.setText(String.valueOf(serviceCost));

    }
    public void setCarLicensePlateField(String carLicensePlate) {
        carLicensePlateField.setText(carLicensePlate);

    }
    public String getCarLicensePlateField() {
        return carLicensePlateField.getText();
    }
}
