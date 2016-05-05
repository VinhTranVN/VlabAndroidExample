package com.vlab.daggerexample.model;

import javax.inject.Inject;

/**
 * Created by vlab.smartapps@gmail.com on 5/5/16.
 */
public class Vehicle {

    private Motor motor;

    @Inject
    public Vehicle(Motor motor){
        this.motor = motor;
    }

    public void increaseSpeed(int value){
        motor.accelerate(value);
    }

    public void stop(){
        motor.brake();
    }

    public int getSpeed(){
        return motor.getRpm();
    }
}
