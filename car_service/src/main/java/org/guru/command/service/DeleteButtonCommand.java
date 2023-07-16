package org.guru.command.service;

import org.guru.command.Command;
import org.guru.model.Car;
import org.guru.model.ServiceHistory;
import org.guru.view.CarProcessesUI;
import org.guru.view.ServiceProcessesUI;

import javax.swing.*;

public class DeleteButtonCommand implements Command {

    private final ServiceProcessesUI serviceProcessesUI;

    public DeleteButtonCommand(ServiceProcessesUI serviceProcessesUI) {
        this.serviceProcessesUI = serviceProcessesUI;
    }
    @Override
    public void execute() {
        ServiceHistory serviceHistory = new ServiceHistory();
        serviceHistory.setCarLicensePlate(serviceProcessesUI.carLicensePlateField.getText());
        serviceProcessesUI.clear();
        JOptionPane.showMessageDialog(null, serviceHistory.getServiceId()+" silindi.");

    }
}
