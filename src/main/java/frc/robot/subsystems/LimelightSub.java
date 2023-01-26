package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSub extends SubsystemBase {
    public static double
        x = 0, y = 0, area = 0;
    
    private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private static NetworkTableEntry
        tx = table.getEntry("tx"),
        ty = table.getEntry("ty"),
        ta = table.getEntry("ta");
    
    // sets limelight settings to defaults
    static {
        table.getEntry("ledMode").setValue(3);
        table.getEntry("camMode").setValue(0);
    }

    private static boolean lights = true;
    public static void toggleLight() {
        table.getEntry("ledMode").setValue((lights = !lights) ? 3 : 1);
        table.getKeys().forEach(key -> System.out.println(key));
    }

    private static boolean visionProcessing = true;
    public static void toggleCamMode() {
        table.getEntry("camMode").setValue((visionProcessing = !visionProcessing) ? 0 : 1);
    }

    @Override public void periodic() {
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
    }
}