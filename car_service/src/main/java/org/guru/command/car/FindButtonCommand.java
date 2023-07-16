package org.guru.command.car;

import org.guru.command.Command;
import org.guru.model.Car;
import org.guru.view.CarProcessesUI;

import java.sql.Date;

public class FindButtonCommand implements Command {

    private final CarProcessesUI carProcessesUI;


    public FindButtonCommand(CarProcessesUI carProcessesUI){
        this.carProcessesUI = carProcessesUI;
    }


    @Override
    public void execute() {
        Car car = new Car().findSqlSentence(carProcessesUI.getlicensePlate());
        carProcessesUI.setchassisNumber(car.getChassisNumber());
        carProcessesUI.setbrand(car.getBrand());
        carProcessesUI.setModel(car.getModel());
        carProcessesUI.setlicensePlate(car.getLicensePlate());
        carProcessesUI.setvehicleOwner(car.getVehicleOwner());
        carProcessesUI.setentryDate(Date.valueOf(String.valueOf(car.getEntryDate())));
    }
}
