package models;

import java.util.Date;

public class Car {

    // properties
    private String colour;
    private String make;
    private String registration;
    private Date registrationDate;
    private Integer acceleration = 0;

    // when you add a constructor, the default constructor disappears
    public Car(String registration) {
        this.registration = registration;
    }

    public Car(String registration, String colour) {
        this.registration = registration;
        this.colour = colour;
    }

    // Access methods to our members (AKA function)
    // getMemberName, setMemberName
    public String getColour() {
        return this.colour; // this, reserve keyword referring to object
    }

    public void setColour(String newColour) {
        this.colour = newColour;
    }

    // getter for make property
    public String getMake() {
        return this.make;
    }

    // setter for make property
    public void setMake(String newMake) {

        switch (newMake.toLowerCase()) {
            case "honda":
            case "mazda":
            case "toyota":
                this.make = newMake.toLowerCase();
                break;
            default:
        }
    }

    public String getRegistration() {
        return registration;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getAcceleration() {
        return this.acceleration;
    }

    public void accelerate() {
        if (this.acceleration < 200) {
            this.acceleration++;
        }
    }

    public void accelerate(Integer n) {
        for (Integer i = 0; i < n; i++) {
            this.accelerate();
        }
    }

    public void decelerate() {
        if (this.acceleration > 0) {
            this.acceleration--;
        }
    }

    // Behaviour - method
    public void horn() {
        System.out.println("horn horn horn");
    }

}
