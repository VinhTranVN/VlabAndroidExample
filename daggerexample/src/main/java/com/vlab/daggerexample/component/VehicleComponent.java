package com.vlab.daggerexample.component;

import com.vlab.daggerexample.module.VehicleModule;
import com.vlab.daggerexample.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vlab.smartapps@gmail.com on 5/5/16.
 */
@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {
    void inject(MainActivity activity);
//    Vehicle provideVehicle();
}
