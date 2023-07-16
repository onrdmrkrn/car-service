package org.guru.command.servicedetails;

import org.guru.command.Command;
import org.guru.model.ServiceDetail;
import org.guru.model.ServiceHistory;
import org.guru.view.ServiceDetailsProcessesUI;
import org.guru.view.ServiceProcessesUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SaveButtonCommand implements Command {

    private final ServiceDetailsProcessesUI serviceDetailsProcessesUI;

    public SaveButtonCommand(ServiceDetailsProcessesUI serviceDetailsProcessesUI) {
        this.serviceDetailsProcessesUI = serviceDetailsProcessesUI;
    }

    @Override
    public void execute() {
        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setOperationToBePerformed(serviceDetailsProcessesUI.getOperationToBePerformed());
        serviceDetail.setServiceCost(serviceDetailsProcessesUI.getServiceCostField());
        serviceDetail.setServiceHistoryServiceId(serviceDetailsProcessesUI.getServiceHistoryId());
        serviceDetail.setServiceHistoryCarLicensePlate(serviceDetailsProcessesUI.getCarLicensePlateField());
        serviceDetail.insertSqlSentence();
        serviceDetailsProcessesUI.clear();
        JOptionPane.showMessageDialog(null, serviceDetail.getServiceHistoryCarLicensePlate()+" plakalı araca servis kaydı oluşturuldu.");

    }
}
