package com.vlab.daggerexample.module;

import com.vlab.daggerexample.model.Motor;
import com.vlab.daggerexample.model.Vehicle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vlab.smartapps@gmail.com on 5/5/16.
 */
@Module
public class VehicleModule {

    @Provides @Singleton
    Motor provideMotor(){
        return new Motor();
    }

    @Provides @Singleton
    Vehicle provideVehicle(){
        return new Vehicle(new Motor());
    }
}
