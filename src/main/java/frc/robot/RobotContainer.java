// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.LED.IndicateLedColor;
import frc.robot.lib.FeatherCAN;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.LEDSubsystem2023;
import frc.robot.subsystems.StatusLED;
import frc.robot.subsystems.SwitchSubsystem;

public class RobotContainer {
    // public LEDSubsystem ledSub = new LEDSubsystem();
    public SwitchSubsystem switchSub;
    public StatusLED statusLEDSub;
    // private LEDSubsystem2023 led2023Sub = new LEDSubsystem2023();
    private FeatherCAN feather = new FeatherCAN(5);

    public RobotContainer() {
        // ledSub = new LEDSubsystem(9);
        statusLEDSub = new StatusLED(this);
        switchSub = new SwitchSubsystem(0);

        feather.Initialize();

        configureBindings();
    }

    private void configureBindings() {
        // opController.button(1).onTrue(ledSub.HaveNoteCommand());
        // opController.button(2).onTrue(ledSub.NoNoteCommand());
        // switchSub.HaveNoteTrigger.toggleOnTrue(
        // Commands.startEnd(
        // ledSub::HaveNote, // when toggle is true, we have a note
        // ledSub::NoNote, // when false, we have none
        // ledSub));

    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }

    public boolean isDisabled() {
        return DriverStation.isDisabled();
    }

    public boolean isRedAlliance() {
        Optional<Alliance> alliance = DriverStation.getAlliance();
        return (alliance.isPresent() && alliance.get() == Alliance.Red);
    }

}
