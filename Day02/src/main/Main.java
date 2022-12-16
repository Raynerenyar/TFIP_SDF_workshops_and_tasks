package main;

import java.util.Date;

import models.Car;
import models.TurboChargedCar;

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("S123");
        Car myOtherCar = new TurboChargedCar("S456");
        Car theCar = new Car("S0232", "Black");

        myCar.setColour("black");
        myCar.setMake("Honda");
        myCar.setRegistrationDate(new Date());

        myCar.horn();
        System.out.printf("colour: %s, make: %s, registration %s", myCar.getColour(), myCar.getMake(),
                myCar.getRegistration());

        myCar.accelerate();
        myCar.accelerate();

        if (myOtherCar instanceof TurboChargedCar) {
            TurboChargedCar turbo = (TurboChargedCar) myOtherCar;
        }
    }
}
