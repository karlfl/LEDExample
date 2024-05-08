// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.BitSet;

import edu.wpi.first.hal.CANData;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class FeatherCAN {
    private int devMfg = 8;
    private int devType = 11;
    private int deviceId;

    private CAN can;
    private byte[] dataToWrite = { 0, 1, 2, 3, 4, 5, 6, 7 };
    private byte[] statusUnknown = { 0, 0, 0, 0, 0, 0, 0, 0 };

    private Notifier n1, n2, n3, n4;

    private byte[] _status = statusUnknown;

    public byte[] Get_Status() {
        requestStatusUpdate();
        return _status;
    }

    public FeatherCAN(int deviceId) {
        super();
        this.deviceId = deviceId;
    }

    public void Initialize() {
        can = new CAN(deviceId, devMfg, devType);

        Set_Num_Of_LEDs(16);

        n1 = new Notifier(() -> requestStatusUpdate());
        n1.startPeriodic(1);

        n2 = new Notifier(() -> Send_PatternChaos());
        n2.startPeriodic(10);

        // n3 = new Notifier(() -> Send_83());
        // n3.startPeriodic(0.2);

        n4 = new Notifier(() -> readButtonPress());
        n4.startPeriodic(0.2);
    }

    private void requestStatusUpdate() {
        // Request Status Update from FeatherCAN
        // System.out.println("Requesting Status from FeatherCAN");
        writePacket(dataToWrite, FeatherAPIEnum.StatusRequest);
        CANData data = new CANData();
        // wait up to 3 20ms cycles to read the status packet
        boolean result = readPacketTimeout(FeatherAPIEnum.StatusReply, 60, data);
        if (result) {
            _status = data.data;
            // System.out.println(data.length);
            // System.out.println(String.format("Status Received: %s",new String(data.data)));
        } else {
            DriverStation.reportError(String.format(
                    "FeatherCAN, ID:%d, %s",
                    deviceId,
                    "Status message delayed or missing.  Device may be malfunctioning"), false);
            // System.err.println(
            // String.format(
            // "FeatherCAN, id:%d, %s",
            // deviceId,
            // "Status message delayed or missing. Device may be malfunctioning"));
        }
    }

    private void Set_Num_Of_LEDs(int numOfLEDs) {
        var now = Timer.getFPGATimestamp();
        long canMsg = setValue(0L, numOfLEDs, 0, 7);
        canMsg = setValue(canMsg, 30, 8, 15);
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(canMsg);
        System.out.println(binaryFormat(canMsg));
        writePacket(buffer.array(), FeatherAPIEnum.InitPixelArray);
        var end = Timer.getFPGATimestamp();
        SmartDashboard.putNumber("N1", end - now);
    }

    private void Send_PatternChaos() {
        var now = Timer.getFPGATimestamp();
        writePacket(dataToWrite, FeatherAPIEnum.PatternChaos);
        var end = Timer.getFPGATimestamp();
        SmartDashboard.putNumber("N2", end - now);
    }

    private void readButtonPress() {
        var now = Timer.getFPGATimestamp();

        CANData data = new CANData();
        // wait up to 20ms cycles to read the status packet
        boolean result = readPacketTimeout(FeatherAPIEnum.ButtonPress, 20, data);
        if (result) {
            System.out.println(String.format("Feather Button Pressed: %s",new String(data.data)));
        }

        var end = Timer.getFPGATimestamp();
        SmartDashboard.putNumber("N3", end - now);
    }

    private void writePacket(byte[] dataToWrite, FeatherAPIEnum apiId) {
        try {
            can.writePacket(dataToWrite, apiId.getValue());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean readPacket(FeatherAPIEnum apiId, CANData data) {
        try {
            return can.readPacketNew(apiId.getValue(), data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private boolean readPacketTimeout(FeatherAPIEnum apiId, int timeoutMs, CANData data) {
        try {
            return can.readPacketTimeout(apiId.getValue(), timeoutMs, data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public long getValue(long val, int startBit, int endBit) {
        BitSet source = BitSet.valueOf(new long[] { val });
        BitSet result = source.get(startBit, endBit);

        long[] lr = result.toLongArray();
        if (lr.length == 0) {
            return 0;
        }
        return lr[0];
    }

    public long setValue(long target, long val, int startBit, int endBit) {
        // Convert the target to a BitSet object in prep to manipulate
        BitSet result = BitSet.valueOf(new long[] { target });

        // Position the value between the start and end bits
        if (startBit > 0) {
            val = (val << startBit);
        }

        // Convert it to a BitSet
        BitSet valueToSet = BitSet.valueOf(new long[] { val });

        // Clear the trailing bits to protect against overflow
        int len = valueToSet.length();
        if (len > endBit + 1) {
            valueToSet.clear(endBit + 1, len);
        }

        // Apply the bitmask via a bitwise OR operation
        result.or(valueToSet);

        long[] lr = result.toLongArray();
        if (lr.length == 0) {
            return 0;
        }
        return lr[0];
    }

    public static String binaryFormat(Long val) {
        StringBuilder out = new StringBuilder();

        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();
        StringBuilder line3 = new StringBuilder();
        for (int i = 63; i > -1; i--) {
            String v = "" + i;

            if (i == 0 || (i + 1) % 8 == 0) {
                if (i > 9) {
                    line1.append(v.charAt(0));
                    line2.append(v.charAt(1));
                } else {
                    line2.append(v.charAt(0));
                }
                line3.append("|");
            } else {
                line1.append(" ");
                line2.append(" ");
                line3.append(" ");
            }
        }

        BigInteger bi = BigInteger.valueOf(val);
        String binary = bi.toString(2);
        StringBuilder line4 = new StringBuilder();
        for (int i = 0; i < (64 - binary.length()); i++)
            line4.append("0");

        line4.append(BigInteger.valueOf(val).toString(2));

        out.append(line1)
                .append("\n")
                .append(line2)
                .append("\n")
                .append(line3)
                .append("\n")
                .append(line4)
                .append("\n");

        return out.toString();
    }
}
