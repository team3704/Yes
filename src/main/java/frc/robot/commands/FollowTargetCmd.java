package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSub;

public class FollowTargetCmd extends CommandBase {
    private final DrivetrainSubsystem drivetrain;

    public FollowTargetCmd(DrivetrainSubsystem drivetrain) {
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    @Override public void initialize() {
        Robot.manualDrive = false;
    }

    @Override public void execute() {
        if(LimelightSub.area != 0) {
            System.out.println("WOOF WOOF!!\nBARK BARK!!!\nWOOF!!\nWOOF WOOF BARK BARK!!!");
        }
    }

    @Override public void end(boolean interrupted) {
        Robot.manualDrive = true;
    }
}
