package frc.robot;

import static edu.wpi.first.math.MathUtil.applyDeadband;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private final LimelightSub limelight = new LimelightSub();
  public static Joystick joystick = new Joystick(Constants.id_stick);
  public static final ShooterSub shooter = new ShooterSub();
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private static final RotaterSub spinSub = new RotaterSub();
  public static final SpinTestCmd cmd_spinns = new SpinTestCmd(spinSub);

  public RobotContainer() {
    configureBindings();
    spinSub.zero();
  }

  void humanControl() {
    drivetrain.drive(
      applyDeadband(joystick.getX(), 0.2),
      applyDeadband(-joystick.getY(), 0.2),
      0//Math.abs((joystick.getZ() - 1) / 2)
    );
  }

  private void configureBindings() {
    new JoystickButton(joystick, 7).whileTrue(new ShootCmd(shooter));
    new JoystickButton(joystick, 1).whileTrue(new ButtonCmd(spinSub, -1));
    new JoystickButton(joystick, 3).whileTrue(new ButtonCmd(spinSub, 1000));
    new JoystickButton(joystick, 4).whileTrue(new ButtonCmd(spinSub, 1500));
    new JoystickButton(joystick, 2).whileTrue(new ButtonCmd(spinSub, 0));
    new JoystickButton(joystick, 5).whileTrue(new ButtonCmd(spinSub, 500));
    new JoystickButton(joystick, 11).whileTrue(new ButtonCmd(spinSub, -3));
    new JoystickButton(joystick, 10).whileTrue(new ButtonCmd(spinSub, -2));
  }

  public void scheduleCmds() {
    //CommandScheduler.getInstance().schedule(new SpinTestCmd(spinSub));
  }

  public static double getStickZ() {
    return Math.abs((joystick.getZ() - 1) / 2);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new ShootCmd(shooter).andThen(new AutonDriveCmd(drivetrain));
    // Commands.runOnce(() -> {}, subsytems...); also is a command
  }
}
