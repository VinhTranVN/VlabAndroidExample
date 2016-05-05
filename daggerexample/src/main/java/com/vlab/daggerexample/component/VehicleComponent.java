package com.vlab.daggerexample.component;

import com.vlab.daggerexample.model.Vehicle;
import com.vlab.daggerexample.module.VehicleModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vlab.smartapps@gmail.com on 5/5/16.
 */
@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {

    Vehicle provideVehicle();

}
