package org.guru.command.car;

import org.guru.command.Command;
import org.guru.model.Car;
import org.guru.model.ServiceHistory;
import org.guru.view.CarProcessesUI;
import org.guru.view.ServiceProcessesUI;

import javax.swing.*;

public class SaveButtonCommand implements Command {

    private final CarProcessesUI carProcessesUI;
    private final ServiceHistory serviceHistory;

    public SaveButtonCommand(CarProcessesUI carProcessesUI, ServiceHistory serviceHistory) {
        this.carProcessesUI = carProcessesUI;
        this.serviceHistory = serviceHistory;
    }




    @Override
    public void execute() {
        Car car = new Car();
        car.setChassisNumber(carProcessesUI.getchassisNumber());
        car.setBrand(carProcessesUI.getbrand());
        car.setModel(carProcessesUI.getModel());
        car.setLicensePlate(carProcessesUI.getlicensePlate());
        car.setVehicleOwner(carProcessesUI.getvehicleOwner());
        car.setEntryDate(carProcessesUI.getentryDate());
        car.setVehicleOwnerPhoneNumber(carProcessesUI.getVehicleOwnerPhoneNumber());
        car.insertSqlSentence();
        carProcessesUI.clear();

        JOptionPane.showMessageDialog(null, car.getLicensePlate()+" 'lı araç eklendi.");

    }
}
