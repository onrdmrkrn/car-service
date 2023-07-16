package org.guru.command.servicedetails;

import org.guru.command.Command;
import org.guru.model.ServiceDetail;
import org.guru.model.ServiceHistory;
import org.guru.view.ServiceDetailsProcessesUI;
import org.guru.view.ServiceProcessesUI;

public class FindButtonCommand implements Command {

    private final ServiceDetailsProcessesUI serviceDetailsProcessesUI;


    public FindButtonCommand(ServiceDetailsProcessesUI serviceDetailsProcessesUI){
        this.serviceDetailsProcessesUI = serviceDetailsProcessesUI;
    }


    @Override
    public void execute() {
        ServiceDetail serviceDetail = new ServiceDetail().findSqlSentence(serviceDetailsProcessesUI.getCarLicensePlateField());
        serviceDetailsProcessesUI.setServiceCostField(serviceDetail.getServiceCost());
        serviceDetailsProcessesUI.setCarLicensePlateField(serviceDetail.getServiceHistoryCarLicensePlate());
        serviceDetailsProcessesUI.setOperationToBePerformedField(serviceDetail.getOperationToBePerformed());
        serviceDetailsProcessesUI.setServiceHistoryId(serviceDetail.getServiceHistoryServiceId());
    }
}
