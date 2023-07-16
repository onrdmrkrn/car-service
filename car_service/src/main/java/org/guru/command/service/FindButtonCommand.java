package org.guru.command.service;

import org.guru.command.Command;
import org.guru.model.Car;
import org.guru.model.ServiceHistory;
import org.guru.view.CarProcessesUI;
import org.guru.view.ServiceProcessesUI;

import java.sql.Date;

public class FindButtonCommand implements Command {

    private final ServiceProcessesUI serviceProcessesUI;


    public FindButtonCommand(ServiceProcessesUI serviceProcessesUI){
        this.serviceProcessesUI = serviceProcessesUI;
    }


    @Override
    public void execute() {
        ServiceHistory serviceHistory = new ServiceHistory().findSqlSentence(serviceProcessesUI.getCarLicensePlateField());
        serviceProcessesUI.setServiceIdField(serviceHistory.getServiceId());
        serviceProcessesUI.setServiceCostField(serviceHistory.getServiceCost());
        serviceProcessesUI.setServicePerformedByField(serviceHistory.getServicePerformedBy());
        serviceProcessesUI.setOperationToBePerformedField(serviceHistory.getOperationToBePerformed());
        serviceProcessesUI.setServicePerformerPhoneField(serviceHistory.getServicePerformerPhone());
    }
}
