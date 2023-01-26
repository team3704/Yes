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

    private static boolean lights = true;
    public static void toggleLight() {
        table.getEntry("ledMode").setValue((lights = !lights) ? 3 : 1);
    }

    @Override public void periodic() {
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
    }
}