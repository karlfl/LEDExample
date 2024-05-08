package frc.robot.lib;

public enum FeatherAPIEnum {
        StatusRequest(0x00),
        StatusReply(0x01),
        SetDeviceNumber(0x10),
        InitPixelArray(0x11),

        PatternChaos(0x20),
        PatternRainbow(0x21),
        PatternSolid(0x22),
        PatternBlink(0x23),
        PatternIntensity(0x24),
        PatternScanner(0x25),
        PatternAlternating(0x26),
        PatternChase(0x27), 

        ButtonPress(0x30);
            
        private int value;
        private FeatherAPIEnum(int value) {
           this.value = value;
        }
        public int getValue() {
           return value;
        }

}
