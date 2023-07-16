package org.guru.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockComplexType {
    private int carId;
    private String chassisNumber;
    private String brand;
    private String model;
    private String vehicleOwner;
    private String vehicleOwnerPhoneNumber;
    private java.util.Date entryDate;
    private String licensePlate;
    public Object[] getInfo(){
        Object[] info={carId,chassisNumber,brand,model,licensePlate,vehicleOwner,vehicleOwnerPhoneNumber,entryDate};
        return info;
    }
    @Override
    public String toString() {
        return chassisNumber;
    }

}
