// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.trobot5013lib.led.TrobotAddressableLED;
import frc.robot.trobot5013lib.led.LEDConstants;

public class StatusLED extends SubsystemBase {
    private TrobotAddressableLED m_led = new TrobotAddressableLED(Constants.LED.STATUS_LED_PWM_PORT, Constants.LED.LED_LENGTH);
    private RobotContainer m_RobotContainer;

    public StatusLED(RobotContainer robotContainer) {
        super();
        m_RobotContainer = robotContainer;
    }

    @Override
    public void periodic() {
        // Set LED Pattern based on robot status
        if (m_RobotContainer.isDisabled()) {
            // ROBOT DISABLED
            m_led.setPattern(LEDConstants.CHASE_RED_BLUE);

        } else if (m_RobotContainer.switchSub.haveNote()) {
            // NOTE IN INTAKE
            m_led.setPattern(LEDConstants.SOLID_ORANGE);

        } else if (m_RobotContainer.isRedAlliance()) {
            // RED ALLIANCE
            m_led.setPattern(LEDConstants.CHASE_RED_GRAY);

        } else {
            // BLUE ALLIANCE (DEFAULT)
            m_led.setPattern(LEDConstants.CHASE_BLUE_GRAY);
        }

    }
}
