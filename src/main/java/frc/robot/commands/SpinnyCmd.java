package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.RotaterSub;

public class SpinnyCmd extends CommandBase {
    RotaterSub climberSub;
    public SpinnyCmd(RotaterSub subsystem) {
        this.climberSub = subsystem;
        addRequirements(climberSub);

    }

    @Override
    public void execute() {
        //climberSub.rotate(MathUtil.applyDeadband(RobotContainer.joystick.getY(), 0.2));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
