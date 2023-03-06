package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class AutonDriveCmd extends CommandBase {
    private final DriveSub driveTrain;
    private Timer t = new Timer();

    public AutonDriveCmd(DriveSub driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.arcadeDrive(0, 0.15, 1);
        t.start();
    }

    @Override
    public boolean isFinished() {
        return t.get() > 1;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.arcadeDrive(0, 0, 0);
    }
}
