package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorTestSub;

public class ElevatorTestCmd extends CommandBase {
    private final ElevatorTestSub elevator;

    public ElevatorTestCmd(ElevatorTestSub elevator) {
        this.elevator = elevator;
        addRequirements(elevator);
    }

    @Override public void execute() {elevator.setPosition(RobotContainer.joystick.getY() * 300000);}
}
