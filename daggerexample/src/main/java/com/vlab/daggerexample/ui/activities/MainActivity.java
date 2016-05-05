package com.vlab.daggerexample.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.vlab.daggerexample.R;
import com.vlab.daggerexample.component.DaggerVehicleComponent;
import com.vlab.daggerexample.component.VehicleComponent;
import com.vlab.daggerexample.model.Vehicle;
import com.vlab.daggerexample.module.VehicleModule;

public class MainActivity extends AppCompatActivity {

    private Vehicle mVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();

        mVehicle = component.provideVehicle();
        mVehicle.increaseSpeed(10);

        Toast.makeText(this, String.valueOf(mVehicle.getSpeed()), Toast.LENGTH_SHORT).show();
    }
}
