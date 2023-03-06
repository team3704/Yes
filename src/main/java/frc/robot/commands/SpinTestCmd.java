package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RotaterSub;

import static frc.robot.RobotContainer.joystick;
import static java.lang.Math.*;

public class SpinTestCmd extends CommandBase {
    public static boolean manualControl = false;
    public static double p = 0;
    private final RotaterSub rotater;

    public SpinTestCmd(RotaterSub rotater) {
        this.rotater = rotater;
        //RotaterSub.pid.setTolerance(45 * Constants.pidMulti_TESTING);
        addRequirements(rotater);
    }

    @Override public void
    execute() {
        double x = joystick.getX();
        rotater.targetPosition = (p = atan(
            sqrt(1 - pow(x, 2)) / x
        ));
    }

    @Override public boolean
    isFinished() {return false;}

    @Override public void
    end(boolean interrupted) {}
}
