package frc.robot.subsystems;

import javax.swing.RowFilter.ComparisonType;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSub extends SubsystemBase {
    TalonFX armMotor = new TalonFX(4);
    
    public void spin(double value) {
        armMotor.set(ControlMode.PercentOutput, value);
    }
}
