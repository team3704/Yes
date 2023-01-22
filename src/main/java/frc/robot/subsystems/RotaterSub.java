package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenixpro.hardware.CANcoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RotaterSub extends SubsystemBase {
    private final TalonFX climbMotor = new TalonFX(Constants.idCl_climber);
    //PIDController pid = new PIDController(0, 0, 0);
    public RotaterSub() {}

    public void spin(double output) {
        climbMotor.set(ControlMode.PercentOutput, output);
    }

    public void zero() {climbMotor.setSelectedSensorPosition(0);}

    public double getAngle() {
        double angle = Math.abs(climbMotor.getSelectedSensorPosition() / 277.77777) % 360;
        return angle;
    }
}
