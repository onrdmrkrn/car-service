package org.guru.command.servicedetails;

import org.guru.command.Command;
import org.guru.model.ServiceDetail;
import org.guru.model.ServiceHistory;
import org.guru.view.ServiceDetailsProcessesUI;
import org.guru.view.ServiceProcessesUI;

import javax.swing.*;

public class UpdateButtonCommand implements Command {

    private final ServiceDetailsProcessesUI serviceDetailsProcessesUI;

    public UpdateButtonCommand(ServiceDetailsProcessesUI serviceDetailsProcessesUI) {
        this.serviceDetailsProcessesUI = serviceDetailsProcessesUI;
    }


    @Override
    public void execute() {

        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setOperationToBePerformed(serviceDetailsProcessesUI.getOperationToBePerformed());
        serviceDetail.setServiceCost(serviceDetailsProcessesUI.getServiceCostField());
        serviceDetail.setServiceHistoryServiceId(serviceDetailsProcessesUI.getServiceHistoryId());
        serviceDetail.setServiceHistoryCarLicensePlate(serviceDetailsProcessesUI.getCarLicensePlateField());
        serviceDetail.updateSqlSentence();
        serviceDetailsProcessesUI.clear();
        JOptionPane.showMessageDialog(null, serviceDetail.getClass()+" plakalı aracın servis durumu güncellendi.");


    }
}
