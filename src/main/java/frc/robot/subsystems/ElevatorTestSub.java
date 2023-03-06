package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Dashboard;

public class ElevatorTestSub extends SubsystemBase {
    private final TalonFX testMotor = new TalonFX(Constants.id_Elevator);
    private final PIDController pid = new PIDController(0.00002, 0.0000005, 0.0000002);
    public ElevatorTestSub() {
        pid.setTolerance(50000);
    }

    public void setPosition(double position) {
        testMotor.set(ControlMode.PercentOutput, pid.calculate(testMotor.getSelectedSensorPosition(), position));
    }

    @Override public void periodic() {
        Dashboard.update("position", testMotor.getSelectedSensorPosition());
    }
}
