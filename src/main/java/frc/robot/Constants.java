// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Must be a PWM header, not MXP or DIO
    public static final class LED {
        public static final Color NOTE_COLOR = new Color(255, 94, 0); // Orange
        public static final int STATUS_LED_PWM_PORT = 0;
        public static final int LED_LENGTH = 30;
    }

    public static final class SENSOR {
        public static final int SENSOR_DIO_PORT = 0;
    }

    public static final Color CubeColor = new Color(255, 0, 255); // purple
    public static final Color ConeColor = new Color(255, 255, 0); // yellow
}