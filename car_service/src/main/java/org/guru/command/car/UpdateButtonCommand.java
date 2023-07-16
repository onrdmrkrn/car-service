package org.guru.command.car;

import org.guru.command.Command;
import org.guru.model.Car;
import org.guru.view.CarProcessesUI;

import javax.swing.*;

public class UpdateButtonCommand implements Command {

    private final CarProcessesUI carProcessesUI;

    public UpdateButtonCommand(CarProcessesUI carProcessesUI) {
        this.carProcessesUI = carProcessesUI;
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
        car.updateSqlSentence();
        carProcessesUI.clear();
        JOptionPane.showMessageDialog(null, car.getLicensePlate()+" 'lı aracın durumu güncellendi.");


    }
}
