package frc.robot.trobot5013lib.led;

import edu.wpi.first.wpilibj.util.Color;

public class LEDConstants {
    // private TrobotAddressableLEDPattern m_disabledPattern = new ChasePattern(new
    // Color[]{Color.kDarkRed,Color.kWhite},3);
    public static final TrobotAddressableLEDPattern DISABLED_PATTERN = new RainbowPattern();

    public static final TrobotAddressableLEDPattern SOLID_BLUE = new SolidColorPattern(Color.kDarkBlue);
    public static final TrobotAddressableLEDPattern SOLID_RED = new SolidColorPattern(Color.kDarkRed);
    public static final TrobotAddressableLEDPattern SOLID_GREEN = new SolidColorPattern(Color.kGreen);
    public static final TrobotAddressableLEDPattern SOLID_ORANGE = new SolidColorPattern(new Color(240,70,0));
    public static final TrobotAddressableLEDPattern SOLID_YELLLOW = new SolidColorPattern(Color.kDarkGoldenrod);
    public static final TrobotAddressableLEDPattern SOLID_PURPLE = new SolidColorPattern(Color.kPurple);

    public static final TrobotAddressableLEDPattern BLINKING_RED = new BlinkingPattern(Color.kDarkRed, 0.25);
    public static final TrobotAddressableLEDPattern BLINK_GREEN = new BlinkingPattern(Color.kGreen, 0.25);

    public static final TrobotAddressableLEDPattern INTENSITY_BLUE = new IntensityPattern(Color.kDarkBlue, 0);
    public static final TrobotAddressableLEDPattern INTENSITY_RED = new IntensityPattern(Color.kDarkRed, 0);

    private static final Color[] RED_GRAY_ARRAY = { Color.kDarkRed, Color.kDarkGray };
    private static final Color[] BLUE_GRAY_ARRAY = { Color.kDarkBlue, Color.kDarkGray };
    private static final Color[] RED_BLACK_ARRAY = { Color.kDarkRed, Color.kBlack };
    private static final Color[] BLUE_BLACK_ARRAY = { Color.kDarkBlue, Color.kBlack };
    private static final Color[] RED_BLUE_ARRAY = { Color.kDarkRed, Color.kDarkBlue };
    public static final TrobotAddressableLEDPattern CHASE_RED_GRAY = new ChasePattern(RED_GRAY_ARRAY, 7);
    public static final TrobotAddressableLEDPattern CHASE_BLUE_GRAY = new ChasePattern(BLUE_GRAY_ARRAY, 7);
    public static final TrobotAddressableLEDPattern CHASE_RED_BLUE = new ChasePattern(RED_BLUE_ARRAY, 5);

    public static final TrobotAddressableLEDPattern chaos = new ChaosPattern();

}
