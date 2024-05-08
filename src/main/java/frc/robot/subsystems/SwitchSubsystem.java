// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;

public class SwitchSubsystem extends SubsystemBase {

    public Trigger HaveNoteTrigger;
    private DigitalInput intakeSensor = new DigitalInput(Constants.SENSOR.SENSOR_DIO_PORT);

    /** Creates a new SwitchSubsystem. */
    public SwitchSubsystem(int sensor_DIO_port) {
        HaveNoteTrigger = new Trigger(this::haveNote);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public boolean haveNote() {
        return !intakeSensor.get();
    }

}
