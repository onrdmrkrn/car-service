package org.guru.view;

import javax.swing.*;
public class ServiceProcessesUI extends JPanel {

    private static ServiceProcessesUI serviceProcessesUI;

    public static ServiceProcessesUI getInstance() {
        if (serviceProcessesUI == null) {
            serviceProcessesUI = new ServiceProcessesUI();
        }
        return serviceProcessesUI;
    }
    public JLabel serviceIdLabel = new JLabel("Servis Id:", JLabel.LEFT);

    public JTextField serviceIdField = new JTextField();
    public JLabel reasonForServiceLabel = new JLabel("Servise Gelme Nedeni:", JLabel.LEFT);
    public JTextField reasonForServiceField = new JTextField();
    public JTextField operationToBePerformedField = new JTextField();

    public JTextField serviceCostField = new JTextField();
    public JLabel servicePerformedByLabel = new JLabel("Servisi Yapan Kişi:", JLabel.LEFT);
    public JTextField servicePerformedByField = new JTextField();
    public JLabel servicePerformerPhoneLabel = new JLabel("Servisi Yapan Kişi Numarası:", JLabel.LEFT);
    public JTextField servicePerformerPhoneField = new JTextField();
    public JLabel carLicensePlateLabel = new JLabel("Araç Plakası:", JLabel.LEFT);
    public JTextField carLicensePlateField = new JTextField();

    public JLabel serviceNoteLabel = new JLabel("Servis Notu:", JLabel.LEFT);
    public JTextField serviceNoteField = new JTextField();


    public JButton saveButton = new JButton("İşlem Kaydet");

    public JButton updateButton = new JButton("İşlem Güncelle");

    public JButton findButton = new JButton("İşlem Ara");

    public JButton deleteButton = new JButton("İşlem Sil");

    public ServiceProcessesUI() {
        initPanel();
    }
    public void initPanel() {
        add(carLicensePlateLabel);
        add(carLicensePlateField);
        add(reasonForServiceLabel);
        add(reasonForServiceField);
        add(servicePerformedByLabel);
        add(servicePerformedByField);
        add(servicePerformerPhoneLabel);
        add(servicePerformerPhoneField);
        add(serviceNoteLabel);
        add(serviceNoteField);
        add(saveButton);
        add(updateButton);
        add(deleteButton);
    }
    public void clear() {
        carLicensePlateField.setText("");
        serviceNoteField.setText("");
        reasonForServiceField.setText("");
        operationToBePerformedField.setText("");
        serviceCostField.setText("");
        servicePerformedByField.setText("");
        servicePerformerPhoneField.setText("");
    }
    public String getOperationToBePerformed() {
        return operationToBePerformedField.getText();
    }

    public String getReasonForServiceField() {
        return reasonForServiceField.getText();
    }

    public String getOperationToBePerformedField() {
        return operationToBePerformedField.getText();
    }

    public Double getServiceCostField() {
        return Double.valueOf(serviceCostField.getText());
    }

    public String getServicePerformedByField() {
        return servicePerformedByField.getText();
    }

    public String getServicePerformerPhoneField() {
        return servicePerformerPhoneField.getText();
    }
    public void setServiceIdField(int serviceId) {
        serviceIdField.setText(serviceId + "");

    }
    public void setReasonForServiceField(String reasonForService) {
        reasonForServiceField.setText(reasonForService);

    }
    public void setOperationToBePerformedField(String operationToBePerformed) {
        operationToBePerformedField.setText(operationToBePerformed);

    }
    public void setServiceCostField(Double serviceCost) {
        serviceCostField.setText(String.valueOf(serviceCost));

    }
    public void setServicePerformedByField(String servicePerformedBy) {
        servicePerformedByField.setText(servicePerformedBy);

    }
    public void setServicePerformerPhoneField(String servicePerformerPhone) {
        servicePerformerPhoneField.setText(servicePerformerPhone);

    }
    public void setCarLicensePlateField(String carLicensePlate) {
        carLicensePlateField.setText(carLicensePlate);

    }
    public String getCarLicensePlateField() {
        return carLicensePlateField.getText();
    }
    public String getServiceNoteField() {
        return serviceNoteField.getText();
    }
    public void setServiceNoteField(String serviceNote) {
        serviceNoteField.setText(serviceNote);

    }

}
