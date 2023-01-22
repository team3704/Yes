package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//import static java.lang.System.out;

public class ButtonCmd extends CommandBase {
    private int targetAngle;

    public ButtonCmd(int angle) {
        this.targetAngle = angle;
    }
    
    /*@Override
    public void execute() {
        if(targetAngle == -1) climber.zero();
        else {
            double currentAngle = climber.getAngle();
            if(!targetMet(currentAngle)) {
                if(currentAngle > targetAngle - 5) {
                    climber.rotate(-0.2);
                    out.println(currentAngle + " greater than taget angle " + targetAngle);
                }
                if(currentAngle < targetAngle + 5) {
                    climber.rotate(0.2);
                    out.println(currentAngle + " less than taget angle " + targetAngle);}
            }
        }
    }*/

    //@Override 
    //public boolean isFinished() {return targetMet(climber.getAngle());}

    /*@Override
    public void end(boolean interrupted) {
        if(targetMet(climber.getAngle()) ||
        !RobotContainer.joystick.getRawButton(3) &&
        !RobotContainer.joystick.getRawButton(4) &&
        !RobotContainer.joystick.getRawButton(2) &&
        !RobotContainer.joystick.getRawButton(5)
        ) climber.rotate(0);
    }*/

    public boolean targetMet(double currentAngle) {
        return
        (((targetAngle - 5) > 0) ? (currentAngle > (targetAngle - 5)) : (currentAngle > (targetAngle - 5 + 360))) &&
        (targetAngle + 5 < 360 ? currentAngle < targetAngle + 5 : currentAngle > targetAngle + 5 - 360);
    }
}