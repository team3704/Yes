package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSub extends SubsystemBase {
    TalonFX shooterMotor = new TalonFX(Constants.idSh_shooter);

    public void setOutput(double output) {
        shooterMotor.set(ControlMode.PercentOutput, output);
    }
}
