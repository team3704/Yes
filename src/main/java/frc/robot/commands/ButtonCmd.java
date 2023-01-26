package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.RotaterSub;

public class ButtonCmd extends CommandBase {
    private int targetPosition = 0;
    private final RotaterSub rotater;

    public ButtonCmd(RotaterSub rotater, int targetPosition) {
        this.rotater = rotater;
        this.targetPosition = targetPosition < 0 ? targetPosition : (int) (targetPosition * Constants.pidMulti_TESTING);
        addRequirements(rotater);
    }
    
    @Override
    public void execute() {
        if(targetPosition < 0) {
            System.out.println(targetPosition);
            switch(targetPosition) {
                case -1: rotater.zero(); break;
                case -2: 
                    SpinTestCmd.manualControl = !SpinTestCmd.manualControl;
                    if(SpinTestCmd.manualControl)
                        CommandScheduler.getInstance().schedule(RobotContainer.cmd_spinns);
                    else CommandScheduler.getInstance().cancel(RobotContainer.cmd_spinns);
                    return;
                case -3:
                    System.exit(18313);
                    System.out.println("exitting");
                    break;
                default: System.out.println("Uhm"); break;
            }

        }
        else if(!SpinTestCmd.manualControl) rotater.targetPosition = this.targetPosition;
        RotaterSub.pid.reset();
    }
    @Override 
    public boolean isFinished() {return true;}
}