package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ArmSub;

public class ArmCmd extends CommandBase {
    private final ArmSub arm;
    public ArmCmd(ArmSub arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override public void execute() {
        arm.spin(MathUtil.applyDeadband(RobotContainer.joystick.getY(), 0.1));
    }

    @Override public void end(boolean interrupted) {
        arm.spin(0);
    }
}
