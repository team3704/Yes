package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.RotaterSub;

public class SpinTestCmd extends CommandBase {
    public static boolean manualControl = false;
    private final RotaterSub rotater;

    public SpinTestCmd(RotaterSub rotater) {
        this.rotater = rotater;
        addRequirements(rotater);
    }

    @Override public void
    execute() {
        if(manualControl) {
            rotater.spin(MathUtil.applyDeadband(RobotContainer.joystick.getY(), -0.2, 0.2));
            return;
        }
        rotater.spin(0);
    }

    @Override public boolean
    isFinished() {return false;}
}
