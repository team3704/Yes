package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutonDriveCmd extends CommandBase {
    private final DrivetrainSubsystem driveTrain;
    private Timer t = new Timer();

    public AutonDriveCmd(DrivetrainSubsystem driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.drive(0, 0.15, 1);
        t.start();
    }

    @Override
    public boolean isFinished() {
        return t.get() > 1;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.drive(0, 0, 0);
    }
}
