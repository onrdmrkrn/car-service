package org.guru.command.servicedetails;

import org.guru.command.Command;
import org.guru.model.ServiceDetail;
import org.guru.model.ServiceHistory;
import org.guru.view.ServiceDetailsProcessesUI;
import org.guru.view.ServiceProcessesUI;

import javax.swing.*;

public class DeleteButtonCommand implements Command {

    private final ServiceDetailsProcessesUI serviceDetailsProcessesUI;

    public DeleteButtonCommand(ServiceDetailsProcessesUI serviceDetailsProcessesUI) {
        this.serviceDetailsProcessesUI = serviceDetailsProcessesUI;
    }
    @Override
    public void execute() {
        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setServiceHistoryCarLicensePlate(serviceDetailsProcessesUI.carLicensePlateField.getText());
        serviceDetail.setServiceDetailId(Integer.parseInt(serviceDetailsProcessesUI.serviceDetailIdField.getText()));
        serviceDetailsProcessesUI.clear();
        JOptionPane.showMessageDialog(null, serviceDetail.getServiceHistoryCarLicensePlate()+"'lı aracın servis detay kaydı silindi.");

    }
}
