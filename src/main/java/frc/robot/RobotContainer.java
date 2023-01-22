package frc.robot;

import static edu.wpi.first.math.MathUtil.applyDeadband;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  public static Joystick joystick = new Joystick(Constants.id_stick);
  public static final ShooterSub shooter = new ShooterSub();
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private static final RotaterSub spinSub = new RotaterSub();
  public static final SpinnyCmd spinn = new SpinnyCmd(spinSub);

  public RobotContainer() {
    configureBindings();
  }

  void humanControl() {
    drivetrain.drive(
      applyDeadband(joystick.getX(), 0.2),
      applyDeadband(-joystick.getY(), 0.2),
      Math.abs((joystick.getZ() - 1) / 2)
    );
  }

  private void configureBindings() {
    new JoystickButton(joystick, 7).whileTrue(new ShootCmd(shooter));
    new JoystickButton(joystick, 1).whileTrue(new ButtonCmd(-1));
    new JoystickButton(joystick, 3).whileTrue(new ButtonCmd(90));
    new JoystickButton(joystick, 4).whileTrue(new ButtonCmd(180));
    new JoystickButton(joystick, 2).whileTrue(new ButtonCmd(270));
    new JoystickButton(joystick, 5).whileTrue(new ButtonCmd(0));
  }

  public void scheduleCmds() {
    CommandScheduler.getInstance().schedule(new SpinTestCmd(spinSub));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new ShootCmd(shooter).andThen(new AutonDriveCmd(drivetrain));
  }
}
