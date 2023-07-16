package org.guru.command.service;

import org.guru.command.Command;
import org.guru.model.Car;
import org.guru.model.ServiceHistory;
import org.guru.view.CarProcessesUI;
import org.guru.view.ServiceProcessesUI;

import javax.swing.*;

public class UpdateButtonCommand implements Command {

    private final ServiceProcessesUI serviceProcessesUI;

    public UpdateButtonCommand(ServiceProcessesUI serviceProcessesUI) {
        this.serviceProcessesUI = serviceProcessesUI;
    }


    @Override
    public void execute() {

        ServiceHistory serviceHistory = new ServiceHistory();
        serviceHistory.setOperationToBePerformed(serviceProcessesUI.getOperationToBePerformed());
        serviceHistory.setServicePerformedBy(serviceProcessesUI.getServicePerformedByField());
        serviceHistory.setServicePerformerPhone(serviceProcessesUI.getServicePerformerPhoneField());
        serviceHistory.setCarLicensePlate(serviceProcessesUI.getCarLicensePlateField());
        serviceHistory.updateSqlSentence();
        serviceProcessesUI.clear();
        JOptionPane.showMessageDialog(null, serviceHistory.getCarLicensePlate()+" 'lı aracın servis durumu güncellendi.");


    }
}
