package frc.robot.subsystems.drivetrain;

import frc.robot.subsystems.Controller.AxisMap;

public interface DriveSub {
    public abstract void arcadeDrive(AxisMap xAxisMap, AxisMap yAxisMap);
    public abstract void tankDrive(AxisMap leftAxisMap, AxisMap rightAxisMap);
}
