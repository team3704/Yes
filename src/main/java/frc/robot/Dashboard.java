package frc.robot;

import frc.robot.commands.SpinTestCmd;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.RotaterSub;

import static edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.*;

public class Dashboard {
    public static void initialize() {
        new Thread(() -> {
            while(true) {
                putNumber("LimelightX", LimelightSub.x);
                putNumber("LimelightY", LimelightSub.y);
                putNumber("LimelightArea", LimelightSub.area);
                putNumber("Angle", Math.toDegrees(SpinTestCmd.p));
                putNumber("Encoder Reading", RotaterSub.getReading());
                putNumber("calculatedOutput", RotaterSub.calculated);
                try {Thread.sleep(20);}
                catch (InterruptedException e) {e.printStackTrace(); break;}
            }
        }).start();
    }

    public static void update(String name, double value) {
        putNumber(name, value);
    }
}
