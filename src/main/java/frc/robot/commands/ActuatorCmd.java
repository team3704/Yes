package frc.robot.commands;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ActuatorCmd extends CommandBase {
    public static Servo s2 = new Servo(4);
    public static double y = 0;
    //public static Servo s = new Servo(5);
    //@Override public boolean isFinished() {return true;}

    @Override public void execute() {
        y = Math.abs(RobotContainer.joystick.getY() - 1) / 2;
        //s.set(y);
        //s2.setSpeed(RobotContainer.joystick.getY());
        //s2.set(y);
    }
}
