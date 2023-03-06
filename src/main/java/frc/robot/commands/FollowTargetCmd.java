package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import static frc.robot.subsystems.LimelightSub.*;

import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.LimelightSub;

public class FollowTargetCmd extends CommandBase {
    private final DriveSub drivetrain;

    public FollowTargetCmd(DriveSub drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override public void initialize() {
        Robot.manualDrive = false;
        LimelightSub.light(true);
    }

    @Override public void execute() {
        double y = 0;
        if(area == 0) {drivetrain.arcadeDrive(0, 0, 0); return;}
        if(area < 0.45) y = -0.15;
        else if(area > 0.75) y = 0.15;
        else y = 0;
        if(drivetrain.inverted) y *= -1;
        if(x > 4) drivetrain.arcadeDrive(0.15 * getXPercent(), y, 1);
        else if(x < -4) drivetrain.arcadeDrive(-0.16 * getXPercent(), y, 1);
        else drivetrain.arcadeDrive(0, y, 1);
    }

    @Override public void end(boolean interrupted) {
        drivetrain.arcadeDrive(0, 0, 0);
        LimelightSub.light(false);
        Robot.manualDrive = true;
    }
}
