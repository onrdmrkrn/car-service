package org.guru.command.car;

import org.guru.command.Command;
import org.guru.model.Car;
import org.guru.view.CarProcessesUI;

import javax.swing.*;

public class DeleteButtonCommand implements Command {

    private final CarProcessesUI carProcessesUI;

    public DeleteButtonCommand(CarProcessesUI carProcessesUI) {
        this.carProcessesUI = carProcessesUI;
    }
    @Override
    public void execute() {
        Car car = new Car();
        car.setLicensePlate(carProcessesUI.getlicensePlate());
        car.deleteSqlSentence();
        carProcessesUI.clear();
        JOptionPane.showMessageDialog(null, car.getLicensePlate()+" deleted.");


    }
}
