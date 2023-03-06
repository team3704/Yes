package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RotaterSub extends SubsystemBase {
    //private static final TalonFX climbMotor = new TalonFX(Constants.idCl_climber);
    // Shooter only for testing things public static PIDController pid = new PIDController(0.00005, 0.0005, 0.000003);
    //public static PIDController pid = new PIDController(0.00008, 0, 0);//0.00002, 0.000005);
    static {
        //pid.setTolerance(0);
        //MusicCmd.add(climbMotor);
    }
    public static double calculated;
    public double targetPosition = 0;

    @Override public void periodic() {
        //calculated = pid.calculate(climbMotor.getSelectedSensorPosition(), targetPosition);
        //climbMotor.set(ControlMode.PercentOutput, calculated / (RobotContainer.getStickZ() + 2));
    }

    //public void zero() {climbMotor.setSelectedSensorPosition(0);}

    public static double getAngle() {
        //double angle = Math.abs(climbMotor.getSelectedSensorPosition() / 277.77777) % 360;
        return 0;//angle;
    }
    public static double getReading() {
        return 0;// climbMotor.getSelectedSensorPosition();
    }
}
