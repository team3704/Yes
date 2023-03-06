package frc.robot;

import static edu.wpi.first.math.MathUtil.applyDeadband;

import frc.robot.Constants.XBoxButtons;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import static edu.wpi.first.wpilibj2.command.Commands.runOnce;
import static java.lang.System.out;

public class RobotContainer {
  public static boolean joystickControl = false;
  private XboxController xboxController = new XboxController(Constants.id_xbox);
  @SuppressWarnings("unused")
  private final LimelightSub limelight = new LimelightSub();
  public static Joystick joystick = new Joystick(Constants.id_stick);
  public static final ShooterSub shooter = new ShooterSub();
  private final DriveSub drivetrain = new DriveSub();
  public final ArmSub armSubsystem = new ArmSub();
  private static final RotaterSub spinSub = new RotaterSub();
  public static final SpinTestCmd cmd_spinns = new SpinTestCmd(spinSub);
  public final ElevatorTestSub elevatorSub = new ElevatorTestSub();

  public RobotContainer() {
    configureBindings();
  }

  void humanControl() {
    if(joystick != null) {
      drivetrain.arcadeDrive(
        applyDeadband(joystick.getX(), 0.2),
        applyDeadband(-joystick.getY(), 0.2),
        getStickZ()
      );
    } else {
      drivetrain.tankDrive(
        applyDeadband(xboxController.getLeftY(), 0.2),
        applyDeadband(xboxController.getRightY(), 0.2)
      );
    }
  }

  private void configureBindings() {
    if(joystick != null) {
      if(!joystick.isConnected()) {Coms.test = true; return;}
      new JoystickButton(joystick, 5).toggleOnTrue(new FollowTargetCmd(drivetrain));
      new JoystickButton(joystick, 7).onTrue(runOnce(() -> LimelightSub.toggleLight()));
      new JoystickButton(joystick, 6).onTrue(runOnce(() -> LimelightSub.toggleCamMode()));
      new JoystickButton(joystick, 10).onTrue(new MusicCmd("thingy.chrp"));
      new JoystickButton(joystick, 8).onTrue(new MusicCmd("notBoring.chrp"));
      new JoystickButton(joystick, 9).onTrue(new MusicCmd("boring.chrp"));
      new JoystickButton(joystick, 1).onTrue(runOnce(() -> drivetrain.invert()));

      new JoystickButton(joystick, 11).onTrue(runOnce(() -> {
        Coms.test = !Coms.test;
        out.println("Pico: " + Coms.test);
      }));
      new JoystickButton(joystick, 4).toggleOnTrue(new ArmCmd(armSubsystem));
    } else if(xboxController != null) {
      System.out.println("XboxButton Mapping");
      new Trigger(() -> {return xboxController.getRawButton(XBoxButtons.X);})
        .onTrue(runOnce(() -> drivetrain.invert()));
      new Trigger(() -> xboxController.getRawButton(XBoxButtons.Y)).onTrue(new FollowTargetCmd(drivetrain));
      /*new Trigger(null).onTrue();
      new Trigger(null).onTrue();
      new Trigger(null).onTrue();*/
    }
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
