package com.vlab.daggerexample.model;

import javax.inject.Inject;

/**
 * Created by vlab.smartapps@gmail.com on 5/5/16.
 */
public class Motor {

    private int rpm;

    @Inject
    public Motor(){
        this.rpm = 0;
    }

    public int getRpm(){
        return rpm;
    }

    public void accelerate(int value){
        rpm = rpm + value;
    }

    public void brake(){
        rpm = 0;
    }
}
