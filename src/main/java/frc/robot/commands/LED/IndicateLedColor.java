// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.LED;

import frc.robot.subsystems.LEDSubsystem;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;

public class IndicateLedColor extends Command {
    LEDSubsystem ledSub;
    Color gamePieceColor;

  /** Creates a new IndicateCube. */
  public IndicateLedColor(LEDSubsystem subsystem, Color color) {
    
    // Use addRequirements() here to declare subsystem dependencies.
    ledSub = subsystem;
    gamePieceColor = color;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Set Game Piece Color");
    ledSub.setColor(gamePieceColor);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ledSub.setRainbow();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}