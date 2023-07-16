package org.guru.controller;

import org.guru.command.car.DeleteButtonCommand;
import org.guru.command.car.FindButtonCommand;
import org.guru.command.car.SaveButtonCommand;
import org.guru.command.car.UpdateButtonCommand;
import org.guru.core.GeneralAction;
import org.guru.model.ServiceHistory;
import org.guru.view.*;

import java.awt.*;

public class CarAutomationFrameController {
    private CarProcessesUI carProcessesUI;
    private ServiceProcessesUI serviceProcessesUI;
    private ServiceDetailsProcessesUI serviceDetailsProcessesUI;

    private ServiceHistory serviceHistory;
    public CarAutomationFrameController() {
    }

    public void execute() {

        MainWindow mainWindow = new MainWindow();
        carProcessesUI = mainWindow.getStockUI();
        serviceProcessesUI = mainWindow.getServiceProcessesUI();
        serviceDetailsProcessesUI = mainWindow.getServiceDetailsProcessesUI();
        serviceHistory = new ServiceHistory();

        carProcessesUI.saveButton.addActionListener(new GeneralAction(new SaveButtonCommand(carProcessesUI,serviceHistory)));
        carProcessesUI.updateButton.addActionListener(new GeneralAction(new UpdateButtonCommand(carProcessesUI)));
        carProcessesUI.findButton.addActionListener(new GeneralAction(new FindButtonCommand(carProcessesUI)));
        carProcessesUI.deleteButton.addActionListener(new GeneralAction(new DeleteButtonCommand(carProcessesUI)));
        carProcessesUI.setLayout(new GridLayout(14,6));

        serviceProcessesUI.saveButton.addActionListener(new GeneralAction(new org.guru.command.service.SaveButtonCommand(serviceProcessesUI)));
        serviceProcessesUI.updateButton.addActionListener(new GeneralAction(new org.guru.command.service.UpdateButtonCommand(serviceProcessesUI)));
        serviceProcessesUI.findButton.addActionListener(new GeneralAction(new org.guru.command.service.FindButtonCommand(serviceProcessesUI)));
        serviceProcessesUI.deleteButton.addActionListener(new GeneralAction(new org.guru.command.service.DeleteButtonCommand(serviceProcessesUI)));
        serviceProcessesUI.setLayout(new GridLayout(20,6));

        serviceDetailsProcessesUI.saveButton.addActionListener(new GeneralAction(new org.guru.command.servicedetails.SaveButtonCommand(serviceDetailsProcessesUI)));
        serviceDetailsProcessesUI.updateButton.addActionListener(new GeneralAction(new org.guru.command.servicedetails.UpdateButtonCommand(serviceDetailsProcessesUI)));
        serviceDetailsProcessesUI.findButton.addActionListener(new GeneralAction(new org.guru.command.servicedetails.FindButtonCommand(serviceDetailsProcessesUI)));
        serviceDetailsProcessesUI.deleteButton.addActionListener(new GeneralAction(new org.guru.command.servicedetails.DeleteButtonCommand(serviceDetailsProcessesUI)));
        serviceProcessesUI.setLayout(new GridLayout(20,6));

    }
}
