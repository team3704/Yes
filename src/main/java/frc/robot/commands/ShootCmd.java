package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ShooterSub;

public class ShootCmd extends CommandBase {
    private final ShooterSub shooter;
    private final Timer t = new Timer();

    public ShootCmd(ShooterSub shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        if(Robot.isAutonomous) t.start();
    }

    @Override
    public void execute() {
        shooter.setOutput(
            Math.cos(
                Math.toRadians((System.nanoTime() / 6000000) % 360)
            ) / 4
        );
    }

    @Override
    public boolean isFinished() {
        return Robot.isAutonomous && t.get() > 1.5;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setOutput(0);
        t.reset();
    }
}
